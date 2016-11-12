package pt.isel.ls.representation.json;


import java.io.IOException;

public class JSONWriter {

    protected StringBuilder stringBuilder;

    public JSONWriter() {
        this.stringBuilder = new StringBuilder();
    }

    protected void writeString(String string) throws IOException {
            stringBuilder.append('"');
            writeJsonString(string);
            stringBuilder.append('"');
    }

    protected void writeJsonString(String string) throws IOException {
        stringBuilder.append(string);
    }

    protected void writeArrayOpen() throws IOException {
        stringBuilder.append('[');
    }

    protected void writeArrayClose() throws IOException {
        stringBuilder.append("]");

    }

    protected void writeObjectOpen() throws IOException {
       stringBuilder.append("{");
    }

    protected void writeObjectClose() throws IOException {
        stringBuilder.append("}");
    }

    protected void writeMemberSeparator() throws IOException {
        stringBuilder.append(":");
    }

    public void writeSeparator() throws IOException {
        stringBuilder.append(",");
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
