package pt.isel.ls.domain;

import pt.isel.ls.representation.html.HTML;
import pt.isel.ls.representation.json.JSONObject;

import java.io.IOException;

public abstract class ObjectRepresentation {
    protected JSONObject jsonObject;
    protected HTML html;
    public abstract JSONObject getJsonObject() throws IOException;
    public abstract HTML getHtml();

    @Override
    public String toString() {
        if(jsonObject != null)
            jsonObject.toString();
        return null; //html case
    }
}
