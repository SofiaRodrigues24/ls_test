package pt.isel.ls.domain;


import pt.isel.ls.representation.html.HTML;
import pt.isel.ls.representation.html.HTMLTemplate;
import pt.isel.ls.representation.json.JSONArray;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.representation.plain.TextPlain;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Template extends ObjectRepresentation {
    private int tid;
    private String name;
    private String description;

    private List<Task> templateTasks;
    private List<CheckList> checklists;

    public Template () {}
    public Template(int TID) {
        this.tid = TID;
        this.templateTasks = new ArrayList<>();
    }

    public Template(int TID, String name, String description) {
        this(TID);
        this.name = name;
        this.description = description;
    }

    public List<Task> getTemplateTasks() {
        return templateTasks;
    }

    public int getTid() {
        return tid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "TID: "+ tid +", name: "+name+", description: "+description+"" +
                (templateTasks.size()==0?"":"\n\ttasks: "+templateTasks)+""+
                (checklists==null?"":"\n\tchecklists: "+checklists)+"\n";
    }

    @Override
    public TextPlain getTextPlain() {
        return new TextPlain(toString());
    }

    public Template populate(ResultSet rs) throws SQLException {
        this.tid = rs.getInt("tid");
        this.name = rs.getString("temp_name");
        this.description = rs.getString("temp_description");
        this.templateTasks = new ArrayList<>();
        return this;
    }

    public void populateTasks(ResultSet rs) throws SQLException {
        templateTasks.add(new Task().populateTaskTemp(rs));
    }

    public void populateChecklists(ResultSet rs) throws SQLException {
        if(checklists == null)
            checklists = new ArrayList<>();
        checklists.add(new CheckList().populate(rs));
    }


    @Override
    public JSONObject getJsonObject() throws IOException {
        JSONObject jo = new JSONObject();
        JSONArray jaTasks = null, jaChecks = null;

        if(templateTasks != null && templateTasks.size() != 0) {
            jaTasks = new JSONArray();
            for (Task t : templateTasks) {
                jaTasks.add(t.getJsonObject());
            }
        }

        if(checklists != null && checklists.size() != 0) {
            jaChecks = new JSONArray();
            for (CheckList t : checklists) {
                jaChecks.add(t.getJsonObject());
            }
        }

        JSONArray ja = null;
        ja = (jaTasks!=null || jaChecks!=null)?new JSONArray():null;

        if(ja!=null) {
            ja.add(jaTasks);
            ja.add(jaChecks);
        }

        jo.add("class", new JSONArray()
                        .add("template"))
                .add("properties", new JSONObject()
                        .add("tid", tid)
                        .add("name", name)
                        .add("description", description)
                ).add("entities", ja);

        return jo;
    }

    @Override
    public HTML getHTML() {
        return new HTMLTemplate(this);
    }

}
