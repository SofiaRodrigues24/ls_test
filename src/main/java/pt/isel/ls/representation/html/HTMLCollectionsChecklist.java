package pt.isel.ls.representation.html;

import pt.isel.ls.domain.CheckList;

import java.util.Iterator;
import java.util.List;

public class HTMLCollectionsChecklist extends HTMLCollections {


    private String name;
    private List<CheckList> list;

    public HTMLCollectionsChecklist(String name) {
        this.name = name;
    }

    @Override
    public HTMLWriter write(HTMLWriter writer) {

        writer.init();

        writer.start("body");
        writer.start("table border = \"1\" width=\"300\" height=\"150\"");
        writer.start("td colspan = \"3\"");
        writer.writer("CHECKLISTS");
        writer.end("td");
        writer.start("tr");
        for (String s: columns) {
            writer.op("th", s);
        }
        writer.end("tr");

        Iterator<CheckList> iterator = list.iterator();
        while (iterator.hasNext())  {
            CheckList next = iterator.next();
            writer.start("tr");
            writer.op("td", String.valueOf(next.getCid()));
            writer.op("td", next.getName());
            writer.op("td", next.getDescription());
            writer.end("tr");
        }

        writer.end("table");
        writer.end("body");

        writer.end();

        super.writer = writer;
        return writer;
    }

    @Override
    protected void setList(List list) {
        this.list = (List<CheckList>) list;
    }
}
