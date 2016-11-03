package pt.isel.ls.representation.json;


import java.io.IOException;
import java.io.Writer;

public class JSONWriter {

    protected Writer writer;

    public JSONWriter() {}

    public JSONWriter(Writer writer) {
        this.writer = writer;
    }

    protected String writeString(String string) throws IOException {
        if(writer != null) {
            writer.write('"');
            writeJsonString(string);
            writer.write('"');
        }
        return "\""+string+"\"";
    }

    protected String writeJsonString(String string) throws IOException {
        if(writer != null)
            writer.write(string);
        return string;
    }

    protected String writeArrayOpen() throws IOException {
        if(writer != null)
            writer.write('[');
        return "[";
    }

    protected String writeArrayClose() throws IOException {
        if(writer != null)
            writer.write(']');
        return "]";

    }

    protected String writeObjectOpen() throws IOException {
        if(writer != null)
            writer.write('{');
        return "{";
    }

    protected String writeObjectClose() throws IOException {
        if(writer != null)
            writer.write('}');
        return "}";
    }

    protected String writeMemberSeparator() throws IOException {
        if(writer != null)
            writer.write(':');
        return ":";
    }

    public String writeSeparator() throws IOException {
        if(writer != null)
            writer.write(',');
        return ",";
    }

    public void close() throws IOException {
        writer.close();
    }
}
