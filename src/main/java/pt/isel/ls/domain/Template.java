package pt.isel.ls.domain;


import java.util.List;

public class Template {
    private int tid;
    private String name;
    private String description;

    private List<Task> templateTasks;
    private List<CheckList> checklists;

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
}
