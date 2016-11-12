package pt.isel.ls.representation.plain;


import java.io.FileWriter;
import java.io.IOException;

public class TextPlain {

    private String string;

    public TextPlain(String s) {
        this.string = s;
    }

    public void toFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.append(string);
        fileWriter.close();
    }

    @Override
    public String toString() {
        return string;
    }
}
