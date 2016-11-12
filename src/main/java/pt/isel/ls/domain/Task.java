package pt.isel.ls.domain;



import pt.isel.ls.representation.json.JSONArray;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.representation.plain.TextPlain;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Task extends ObjectRepresentation {
    private int lid;
    private String name;
    private String description;


    private boolean isClosed;
    private Date duedate;

    public Task() {}

    public Task(int id, String name, String description) {
        this.lid = id;
        this.name = name;
        this.description = description;
        this.isClosed = false;
    }

    //task_check
    public Task(int id, String name, String description, boolean completed) {
        this(id, name, description);
        this.isClosed = completed;
    }

    public Task(int id, String name, String description, boolean completed, Date duedate) {
        this(id, name, description, completed);
        this.duedate = duedate;
    }

    public int getLid() {
        return lid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public String toString() {
        return "\n\t\tLID: "+ lid +"\n\t\tname: "+name+
                (description==null? "" :"\n\t\tdescription: "+description)+"\n";
    }

    @Override
    public TextPlain getTextPlain() {
        return null;
    }

    public Task populate(ResultSet rs) throws SQLException {
        this.lid = rs.getInt("lid");
        this.name = rs.getString("task_name");
        this.description = rs.getString("task_description");
        this.isClosed =rs.getBoolean("isClosed")? rs.getBoolean("isClosed"):false;
        this.duedate = rs.getDate("task_duedate");
        return this;
    }

    public Task populateTaskTemp(ResultSet rs) throws SQLException {
        this.lid = rs.getInt("lid");
        this.name = rs.getString("task_name");
        this.description = rs.getString("task_description");
        return this;
    }




    @Override
    public JSONObject getJsonObject() throws IOException {
        return new JSONObject()
                .add("class", new JSONArray().add("task"))
                .add("properties",
                        new JSONObject()
                                .add("lid", lid)
                                .add("name", name)
                                .add("description", description)
                                .add("isClosed", isClosed)
                                .add("duedate", duedate)
                );
    }

    public Date getDuedate() {
        return duedate;
    }
}
