package pt.isel.ls.domain;

import pt.isel.ls.representation.html.HtmlObject;
import pt.isel.ls.representation.json.JSONObject;

import java.io.IOException;

public abstract class ObjectRepresentation {
    protected JSONObject jsonObject;
    protected HtmlObject html;
    public abstract JSONObject getJsonObject() throws IOException;
    public abstract HtmlObject getHtml();

    @Override
    public String toString() {
        if(jsonObject != null)
            jsonObject.toString();
        return (html!=null)? html.toString(): null;
    }
}
