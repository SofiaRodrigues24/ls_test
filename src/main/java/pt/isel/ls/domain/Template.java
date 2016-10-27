package pt.isel.ls.domain;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Template {
    private int tid;
    private String name;
    private String description;

    private List<Task> templateTasks;
    private List<CheckList> checklists;

    public Template () {}
    public Template(int TID) {
        this.tid = TID;
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

    public void setTid(int tid) {
        this.tid = tid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemplateTasks(List<Task> templateTasks) {
        this.templateTasks = templateTasks;
    }

    public void setChecklists(List<CheckList> checklists) {
        this.checklists = checklists;
    }

    @Override
    public String toString() {
        return "tid: "+ tid +"\n\tname: "+name+"\n\tdescription: "+description+"\n\t" +
                (templateTasks==null?"":"tasks: "+templateTasks)+"\n"+
                (checklists==null?"":"checklists: "+checklists)+"\n";
    }

    public Template create(ResultSet rs) throws SQLException {
        this.setTid(rs.getInt("tid"));
        this.setName(rs.getString("temp_name"));
        this.setDescription(rs.getString("temp_description"));
        return this;
    }

    public void addTask(ResultSet rs) throws SQLException {
        if(templateTasks == null)
            templateTasks = new ArrayList<>();
        templateTasks.add(new Task().create(rs));
    }

    public void addCheckList(ResultSet rs) throws SQLException {
        if(checklists == null)
            checklists = new ArrayList<>();
        checklists.add(new CheckList().create(rs));
    }
}
