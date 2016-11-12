package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.domain.ObjectRepresentation;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.representation.html.HtmlObject;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.manager.Request;
import pt.isel.ls.manager.Result;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class CommandManager {
    
    private Request request;

    public CommandManager(Request request) {
        this.request = request;
    }

    public HashMap<String, String> getParameters() {
        return request.getParameters();
    }

    public Connection getConnection() throws SQLServerException {
        return DBConnection.getConnection();
    }

    public Result getResult(Result result) throws IOException {
        if(request.getMethod().equals("GET")) {
            String accept = getAccept() ;
            if(accept == null || accept.equals("text/html"))
                return getHtml(result);
            if(accept.equals("application/json"))
                return getJsonObject(result);
        }
        return result;
    }

    private Result<JSONObject> getJsonObject(Result result) throws IOException {
        JSONObject res = ((ObjectRepresentation)result.getResult()).getJsonObject();
        return new Result<JSONObject>(res);
    }

    public Result<HtmlObject> getHtml(Result result) {
        HtmlObject obj = ((ObjectRepresentation)result.getResult()).getHtml();
        return new Result<HtmlObject>(obj);
    }

    public boolean hasFileName() {
        return (request.getHeader() != null) && ((request.getHeader().get("filename")) != null);
    }


    public void createFile(Result result) throws IOException {
        String accept = getAccept();
        if(accept.equals("application/json")) {
            JSONObject res = (JSONObject)result.getResult();
            res.writer(request.getHeader().get("filename"));
        }else {
            //TODO: passar o filename
            //html
        }
    }

    public String getAccept() {
        return request.getHeader()!=null?request.getHeader().get("accept"):null;
    }
}
