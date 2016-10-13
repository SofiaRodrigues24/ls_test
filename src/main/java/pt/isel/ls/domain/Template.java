package pt.isel.ls.domain;


import java.util.List;

public class Template {
    private int tid;
    private String name;
    private String description;

    private List<Task> templateTasks;


    public Template(int TID) {
        this.tid = TID;
    }

    public Template(int TID, String name, String description) {
        this(TID);
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "tid: "+ tid +"\nname: "+name+"description: "+description+"tasks: "+templateTasks+"\n";
    }
}
