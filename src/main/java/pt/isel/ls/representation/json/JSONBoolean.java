package pt.isel.ls.representation.json;

import java.io.IOException;

public class JSONBoolean implements JSONValue {

    private String value;

    public JSONBoolean(boolean value) {
        this.value = value? "true": "false";
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
