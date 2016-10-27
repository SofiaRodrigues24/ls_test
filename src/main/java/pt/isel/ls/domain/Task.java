package pt.isel.ls.domain;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Task {
    private int lid;
    private String name;
    private String description;


    private boolean isClosed;
    private Date duedate;

    public Task() {}

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

    public void setLid(int lid) {
        this.lid = lid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
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

    public Task create(ResultSet rs) throws SQLException {
        this.setLid(rs.getInt("lid"));
        this.setName(rs.getString("task_nme"));
        this.setDescription(rs.getString("task_description"));
        this.setClosed(rs.getBoolean("isClosed"));
        this.setDuedate(rs.getDate("task_duedate"));
        return this;
    }
}
