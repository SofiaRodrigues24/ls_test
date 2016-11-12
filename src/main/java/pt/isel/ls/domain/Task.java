package pt.isel.ls.domain;



import pt.isel.ls.representation.html.*;
import pt.isel.ls.representation.json.JSONArray;
import pt.isel.ls.representation.json.JSONObject;

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

    @Override
    public HtmlObject getHtml() {
        HtmlObject ho = new HtmlObject();
        ho.add(new HtmlNumber("lid", lid));
        ho.add(new HtmlString("name", name));
        ho.add(new HtmlString("description", description));
        ho.add(new HtmlBoolean("isClosed", isClosed));
        ho.add(new HtmlDuedate("duedate", duedate));
        return ho;
    }

    public Date getDuedate() {
        return duedate;
    }
}
