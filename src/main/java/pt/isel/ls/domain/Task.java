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
        return "\n\t\tLID: "+ lid +"\n\t\tname: "+name+
                (description==null? "" :"\n\t\tdescription: "+description)+"\n";
    }
}
