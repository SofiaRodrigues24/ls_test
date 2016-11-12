package pt.isel.ls.manager;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.domain.ObjectRepresentation;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.domain.Result;
import pt.isel.ls.representation.html.HTML;
import pt.isel.ls.representation.html.HTMLWriter;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.representation.plain.TextPlain;

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

    public Result getResultType(Result result) throws IOException {
        if(request.getMethod().equals("GET")) {
            String accept = getAccept() ;
            if(accept == null || accept.equals("text/html"))
                return getHtml(result);
            if(accept.equals("application/json"))
                return getJsonObject(result);
        }
        return getTextPlain(result);
    }

    private Result<TextPlain> getTextPlain(Result result) {
        TextPlain res = ((ObjectRepresentation)result.getResult()).getTextPlain();
        return new Result<>(res);
    }

    private Result<HTML> getHtml(Result result) {
        HTML res = ((ObjectRepresentation)result.getResult()).getHTML();
        res.write(new HTMLWriter());
        return new Result<>(res);
    }


    private Result<JSONObject> getJsonObject(Result result) throws IOException {
        JSONObject res = ((ObjectRepresentation)result.getResult()).getJsonObject();
        return new Result<JSONObject>(res);
    }

    public boolean hasFileName() {
        return (request.getHeader() != null) && ((request.getHeader().get("filename")) != null);
    }


    public void createFile(Result result) throws IOException {
        String accept = getAccept();

        if(accept == null || accept.equals("text/html")) {
            HTML res = (HTML)result.getResult();
            res.toFile(request.getHeader().get("filename"));
        }else if(accept.equals("application/json")){
            JSONObject res = (JSONObject)result.getResult();
            res.toFile(request.getHeader().get("filename"));
        }else {
            TextPlain res = (TextPlain)result.getResult();
            res.toFile(request.getHeader().get("filename"));
        }
    }

    public String getAccept() {
        return request.getHeader()!=null?request.getHeader().get("accept"):null;
    }

    public void consumerResult(Result result) throws IOException {
        if(hasFileName()) {
            createFile(result);
        }else {
            result.print();
        }
    }
}
