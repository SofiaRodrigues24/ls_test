package pt.isel.ls.domain;



import pt.isel.ls.representation.html.HTML;
import pt.isel.ls.representation.html.HTMLChecklist;
import pt.isel.ls.representation.json.JSONArray;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.representation.plain.TextPlain;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckList extends ObjectRepresentation {
    private int cid;
    private String name;
    private String description;
    private Date duedate;
    private boolean completed;

    private List<Task> tasks;
    private List<Tag> tags;

    public CheckList () {}

    public CheckList(int CID) {
        this.cid = CID;
        this.tasks = new ArrayList<>();
    }

    public CheckList(int CID, String name, String description) {
        this(CID);
        this.name = name;
        this.description = description;
    }

    public CheckList(int CID, String name, String description, Date duedate) {
        this(CID, name, description);
        this.duedate = duedate;
    }

    public CheckList(int CID, String name, String description, Date duedate, boolean completed) {
        this(CID, name, description, duedate);
        this.completed = completed;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDuedate() {
        return duedate;
    }

    public boolean getCompleted(){return completed;}


    public List<Tag> getTags() {
        return tags;
    }

    public CheckList populate(ResultSet rs) throws SQLException {
        this.cid = rs.getInt("cid");
        this.name = rs.getString("check_name");
        this.description = rs.getString("check_description");
        this.duedate = rs.getDate("check_duedate");
        this.completed = rs.getBoolean("completed");
        this.tasks = new ArrayList<>();
        this.tags = new ArrayList<>();
        return this;
    }

    public void addTask(ResultSet rs) throws SQLException {
        tasks.add(new Task().populate(rs));
    }

    public void addTag(ResultSet rs) throws SQLException {
        tags.add(new Tag().populate(rs));
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    @Override
    public String toString() {
        return "checklist "+ cid +"\n\tname: "+name+"\n\tdescription: "+description+ "" +
                ((duedate!=null)?("\n\tduedate: "+ duedate):"")+
                ((tasks!= null)? ("\n\ttasks: "+tasks):"") +
                ((tags!= null)? ("\n\ttags: "+tags):"")+
                "\n";
    }

    @Override
    public TextPlain getTextPlain() {
        return new TextPlain(toString());
    }


    @Override
    public JSONObject getJsonObject() throws IOException {

        JSONObject jo = new JSONObject();
        JSONArray ja = null;

        if(tasks.size() != 0) {
            ja = new JSONArray();
            for (Task t : tasks) {
                ja.add(t.getJsonObject());
            }
        }


        jo.add("class", new JSONArray()
                        .add("checklist"))
                .add("properties", new JSONObject()
                        .add("cid", cid)
                        .add("name", name)
                        .add("description", description)
                        .add("isClosed", completed)
                        .add("duedate", duedate)
                ).add("entities", ja);

        return jo;
    }

    @Override
    public HTML getHTML() {
        return new HTMLChecklist(this);
    }


}
