package pt.isel.ls.representation.json;


import java.io.IOException;

public class JSONNumber implements JSONValue {

    private String value;

    public JSONNumber(String value) {
        this.value = value;
    }

    @Override
    public JSONWriter write(JSONWriter writer) throws IOException {
        writer.writeJsonString(value);
        return writer;
    }

    @Override
    public String toString() {
        return value;
    }
}
