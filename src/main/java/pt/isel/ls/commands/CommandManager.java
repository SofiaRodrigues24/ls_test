package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.domain.ObjectRepresentation;
import pt.isel.ls.jbdc.DBConnection;
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
        DBConnection connection = new DBConnection(new SQLServerDataSource());
        return connection.getConnection();
    }

    public Result getResult(Result result) throws IOException {
        if(request.getMethod().equals("GET")) {
            String accept = getAccept() ;
            //TODO: acrescentar o html
            if(accept == null || accept.equals("text/html"))
                return result;
                // return getHtml();
            if(accept.equals("application/json"))
                return getJsonObject(result);
        }
        return result;
    }

    private Result getJsonObject(Result result) throws IOException {
        JSONObject res = ((ObjectRepresentation)result.getResult()).getJsonObject();
        return new Result<JSONObject>(res);
    }

    public Result getHtml() {
        return null;
    }

    public boolean hasFileName() {
        return (request.getHeader().get("filename")) != null;
    }


    public void createFile(Result result) throws IOException {
        String accept = getAccept();
        if(accept.equals("application/json")) {
            JSONObject res = (JSONObject)result.getResult();
            res.writer(request.getHeader().get("filename"));
        }else {
            //TODO: passar o filename
            JSONObject res = (JSONObject)result.getResult();
            //res.
        }
    }

    public String getAccept() {
        return request.getHeader()!=null?request.getHeader().get("accept"):null;
    }
}
