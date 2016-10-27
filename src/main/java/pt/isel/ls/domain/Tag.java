package pt.isel.ls.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Tag {
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

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "\n\t\tLID: "+ gid +"\n\t\tname: "+name+ "\n\t\tcolor: "+color+"\n";
    }

    public Tag create(ResultSet rs) throws SQLException {
        this.setGid(rs.getInt("gid"));
        this.setName(rs.getString("tag_name"));
        this.setColor(rs.getInt("color"));
        return this;
    }
}
