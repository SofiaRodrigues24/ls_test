package pt.isel.ls.representation.html;

import pt.isel.ls.domain.Tag;

import java.util.Iterator;
import java.util.List;

public class HTMLCollectionsTag extends HTMLCollections{


    private String name;
    private List<Tag> list;

    public HTMLCollectionsTag(String name) {
        this.name = name;
    }

    @Override
    public HTMLWriter write(HTMLWriter writer) {


        writer.init();

        writer.start("body");
        writer.start("table border = \"1\" width=\"300\" height=\"150\"");
        writer.start("td colspan = \"3\"");
        writer.writer("TAGS");
        writer.end("td");
        writer.start("tr");
        for (int i = 0; i<columns.length-1; ++i) {
            writer.op("th", columns[i]);
        }
        writer.op("th", "color");

        writer.end("tr");

        Iterator<Tag> iterator = list.iterator();
        while (iterator.hasNext())  {
            Tag next = iterator.next();
            writer.start("tr");
            writer.op("td", String.valueOf(next.getGid()));
            writer.op("td", next.getName());
            writer.op("td", String.valueOf(next.getColor()));
            writer.end("tr");
        }

        writer.end("table");
        writer.end("body");

        writer.end();

        super.writer = writer;
        return writer;
    }

    @Override
    public String toString() {
        return super.writer.toString();
    }

    @Override
    protected void setList(List list) {
        this.list = (List<Tag>)list;
    }
}
