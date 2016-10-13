package pt.isel.ls.domain;


import java.util.Date;

public class Task {
    private int lid;
    private String name;
    private String description;


    private boolean isClosed;
    private Date duedate;

    public Task(int id, String name, String description) {
        this.lid = id;
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "LID: "+ lid +"\nname: "+name+"\ndescription"+description+"\n";
    }
}
