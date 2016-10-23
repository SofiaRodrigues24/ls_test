package pt.isel.ls.domain;


import java.util.List;
import java.sql.Date;

public class CheckList {
    private int cid;
    private String name;
    private String description;
    private Date duedate;
    private boolean completed;
    private int numberOfTasks;

    private List<Task> tasks;


    public CheckList(int CID) {
        this.cid = CID;
    }

    public CheckList(String name, String description, Date duedate, boolean completed) {
        this.name = name;
        this.description = description;
        this.duedate = duedate;
        this.completed = completed;
    }

    public CheckList(int CID, String name, String description, Date duedate, boolean completed) {
        this.cid = CID;
        this.name = name;
        this.description = description;
        this.duedate = duedate;
        this.completed = completed;
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

    public int getCid() {
        return cid;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
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

    @Override
    public String toString() {
        return "checklist "+ cid +"\n\tname: "+name+"\n\tdescription: "+description+ "" +
                ((duedate!=null)?("\n\tduedate: "+ duedate):"")+
                ((tasks!= null)? ("\n\ttasks: "+tasks):"")
                +"\n";
    }
}
