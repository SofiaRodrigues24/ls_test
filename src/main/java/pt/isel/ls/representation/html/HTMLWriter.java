package pt.isel.ls.representation.html;

public class HTMLWriter {
    private StringBuilder stringBuilder;

    public HTMLWriter() {
        stringBuilder = new StringBuilder();
    }

    public void start(String s) {
        stringBuilder.append("<"+s+">");
    }

    public void end(String s) {
        stringBuilder.append("</"+s+">");
    }

    public void writer(String s) {
        stringBuilder.append(s);
    }

    public void init() {
        start("!DOCTYPE html");
        start("html");
    }

    public void end() {
        end("html");
    }

    public void op(String op, String s) {
        start(op);
        writer(s);
        end(op);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
