package pt.isel.ls.representation.html;

import pt.isel.ls.domain.Template;

import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unchecked")
public class HTMLCollectionsTemplate extends HTMLCollections{

    private final String name;
    private List<Template> list;

    public HTMLCollectionsTemplate(String name) {
        this.name = name;
    }

    @Override
    public HTMLWriter write(HTMLWriter writer) {

        writer.init();

        writer.start("body");
        writer.start("table border = \"1\" width=\"300\" height=\"150\"");
        writer.start("td colspan = \"3\"");
        writer.writer("TEMPLATES");
        writer.end("td");
        writer.start("tr");
        for (String s: columns) {
            writer.op("th", s);
        }
        writer.end("tr");

        Iterator<Template> iterator = list.iterator();
        while (iterator.hasNext())  {
            Template next = iterator.next();
            writer.start("tr");
            writer.op("td", String.valueOf(next.getTid()));
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
        this.list = (List<Template>)list;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
