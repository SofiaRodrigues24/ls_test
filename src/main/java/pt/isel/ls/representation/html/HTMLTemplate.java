package pt.isel.ls.representation.html;

import pt.isel.ls.domain.Task;
import pt.isel.ls.domain.Template;

import java.util.Iterator;

public class HTMLTemplate extends HTML {

    private Template template;

    public HTMLTemplate(Template template) {
        this.template = template;
    }

    @Override
    public HTMLWriter write(HTMLWriter writer) {

        writer.init();

        writer.start("body");
        writer.op("h1", "TEMPLATE " + String.valueOf(template.getTid()));
        writer.op("h3", "Name: "+template.getName());
        writer.op("h3", "Desceription: "+template.getDescription());
        writer.start("table border = \"1\" width=\"300\" height=\"150\"");

        writer.start("tr");
        for (String s: columns) {
            writer.op("th", s);
        }
        writer.end("tr");


        Iterator<Task> iterator = template.getTemplateTasks().iterator();
        while (iterator.hasNext())  {
            Task next = iterator.next();
            writer.start("tr");
            writer.op("td", String.valueOf(next.getLid()));
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
}
