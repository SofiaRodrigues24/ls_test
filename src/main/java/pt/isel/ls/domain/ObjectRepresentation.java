package pt.isel.ls.domain;

import pt.isel.ls.representation.html.HTML;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.representation.plain.TextPlain;

import java.io.IOException;

public abstract class ObjectRepresentation {
    protected JSONObject jsonObject;
    protected HTML html;
    public abstract JSONObject getJsonObject() throws IOException;

    public HTML getHTML() {
        return null;
    }

    @Override
    public String toString() {
        if(jsonObject != null)
            jsonObject.toString();
        return (html!=null)? html.toString(): null;
    }


    public abstract TextPlain getTextPlain();
}
