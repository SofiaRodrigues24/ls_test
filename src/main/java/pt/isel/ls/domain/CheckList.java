package pt.isel.ls.domain;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class CheckList {
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

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "checklist "+ cid +"\n\tname: "+name+"\n\tdescription: "+description+ "" +
                ((duedate!=null)?("\n\tduedate: "+ duedate):"")+
                ((tasks!= null)? ("\n\ttasks: "+tasks):"") +
                ((tags!= null)? ("\n\ttags: "+tags):"")+
                "\n";
    }

    public CheckList create(ResultSet rs) throws SQLException {
        this.setCid(rs.getInt("cid"));
        this.setName(rs.getString("check_name"));
        this.setDescription(rs.getString("check_description"));
        this.setDuedate(rs.getDate("check_duedate"));
        this.setCompleted(rs.getBoolean("completed"));
        return this;
    }

    public void addTask(ResultSet rs) throws SQLException {
        if(tasks == null)
            tasks = new ArrayList<>();
        tasks.add(new Task().create(rs));
    }

    public void addTag(ResultSet rs) throws SQLException {
        if(tags == null)
            tags = new ArrayList<>();
        tags.add(new Tag().create(rs));
    }
}
