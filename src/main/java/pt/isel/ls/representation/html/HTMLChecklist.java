package pt.isel.ls.representation.html;

import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Tag;
import pt.isel.ls.domain.Task;

import java.util.Iterator;

/**
 * Created by Eliane on 12/11/2016.
 */
public class HTMLChecklist extends HTML {

    private CheckList checklist;

    public HTMLChecklist(CheckList checkList) {
        this.checklist = checkList;
    }

    @Override
    public HTMLWriter write(HTMLWriter writer) {
        writer.init();

        writer.start("body");
        writer.op("h1", "CHECKLIST " + String.valueOf(checklist.getCid()));
        writer.op("h3", "Name: "+checklist.getName());
        writer.op("h3", "Description: "+checklist.getDescription());
        writer.op("h3", "Completed: "+checklist.getCompleted());

        if(checklist.getDuedate() != null)
            writer.op("h3", "Duedate: "+String.valueOf(checklist.getDuedate()));

        writer.start("table border = \"1\" width=\"300\" height=\"150\"");

        writer.start("tr");
        for (String s: columns) {
            writer.op("th", s);
        }
        writer.end("tr");
        if(checklist.getTasks()!=null) {
            Iterator<Task> iterator = checklist.getTasks().iterator();
            while (iterator.hasNext()) {
                Task next = iterator.next();
                writer.start("tr");
                writer.op("td", String.valueOf(next.getLid()));
                writer.op("td", next.getName());
                writer.op("td", next.getDescription());
                writer.end("tr");
            }
        }
        writer.end("table");



        writer.start("table border = \"1\" width=\"300\" height=\"150\"");

        writer.start("tr");
        for (String s: columns) {
            writer.op("th", s);
        }
        writer.end("tr");
        if(checklist.getTags()!=null) {
            Iterator<Tag> iterator1 = checklist.getTags().iterator();
            while (iterator1.hasNext()) {
                Tag next = iterator1.next();
                writer.start("tr");
                writer.op("td", String.valueOf(next.getGid()));
                writer.op("td", next.getName());
                writer.op("td", String.valueOf(next.getColor()));
                writer.end("tr");
            }
        }
        writer.end("table");

        writer.end("body");

        writer.end();

        super.writer = writer;
        return writer;
    }
}
