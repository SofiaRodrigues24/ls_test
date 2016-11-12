package pt.isel.ls.representation.html;

import java.io.FileWriter;
import java.io.IOException;

public abstract class HTML {

    protected HTMLWriter writer;
    protected final String [] columns = new String[] {"id", "name", "description"};

    public abstract HTMLWriter write(HTMLWriter writer);

    public void toFile(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.append(writer.toString());
        fileWriter.close();
    }

    @Override
    public String toString() {
        return writer.toString();
    }
}
