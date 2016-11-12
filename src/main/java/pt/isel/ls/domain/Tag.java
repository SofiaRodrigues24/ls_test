package pt.isel.ls.domain;

import pt.isel.ls.representation.html.*;
import pt.isel.ls.representation.json.JSONObject;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tag extends ObjectRepresentation {
    private int gid;
    private String name;
    private int color;

    public Tag() {}

    public Tag(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public Tag(int gid, String name, int color) {
        this(name, color);
        this.gid = gid;
    }

    public int getGid() {
        return gid;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "\n\t\tLID: "+ gid +"\n\t\tname: "+name+ "\n\t\tcolor: "+color+"\n";
    }

    public Tag populate(ResultSet rs) throws SQLException {
        this.gid = rs.getInt("gid");
        this.name = rs.getString("tag_name");
        this.color = rs.getInt("color");

        return this;
    }


    @Override
    public JSONObject getJsonObject() throws IOException {
        return new JSONObject()
                .add("class", "tag")
                .add("properties",
                        new JSONObject()
                                .add("gid", gid)
                                .add("name", name)
                                .add("color", color)
                );
    }

    @Override
    public HtmlObject getHtml() {
        HtmlObject ho = new HtmlObject();
        ho.add(new HtmlNumber("gid", gid));
        ho.add(new HtmlString("name", name));
        ho.add(new HtmlNumber("color", color));
        return ho;
    }
}
