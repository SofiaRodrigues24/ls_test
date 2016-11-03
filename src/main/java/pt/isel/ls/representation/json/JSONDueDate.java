package pt.isel.ls.representation.json;

import java.io.IOException;

public class JSONDueDate implements JSONValue {
    private String value;

    public JSONDueDate(String value) {
        this.value = value;
    }

    @Override
    public JSONWriter write(JSONWriter writer) throws IOException {
        writer.writeString(value);
        return writer;
    }

    @Override
    public String toString() {
        return value;
    }
}
