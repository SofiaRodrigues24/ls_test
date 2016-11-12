package pt.isel.ls.representation.json;

import java.io.IOException;
import java.util.*;

public class JSONArray implements JSONValue {

    private List<JSONValue> values;

    public JSONArray() {
        this.values = new ArrayList<>();
    }


    public JSONArray add(int value) {
        values.add(new JSONNumber(Integer.toString(value)));
        return this;
    }

    public JSONArray add(String value) {
        values.add(new JSONString(value));
        return this;
    }

    public JSONArray add(JSONValue value) {
        values.add(value);
        return this;
    }


    @Override
    public JSONWriter write(JSONWriter writer) throws IOException {
        writer.writeArrayOpen();
        Iterator<JSONValue> iter = values.iterator();

        if(iter.hasNext()) {
            iter.next().write(writer);
            while (iter.hasNext()) {
                writer.writeSeparator();
                iter.next().write(writer);
            }
        }

        writer.writeArrayClose();
        return writer;
    }
}
