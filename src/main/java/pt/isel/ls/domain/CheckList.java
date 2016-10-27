package pt.isel.ls.domain;


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

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean getCompleted(){
        return completed;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "checklist "+ cid +"\n\tname: "+name+"\n\tdescription: "+description+ "" +
                ((duedate!=null)?("\n\tduedate: "+ duedate):"")+
                ((tasks!= null)? ("\n\ttasks: "+tasks):"") +
                ((tags!= null)? ("\n\ttags: "+tags):"")+
                "\n";
    }
}
