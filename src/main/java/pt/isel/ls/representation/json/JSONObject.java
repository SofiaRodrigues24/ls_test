package pt.isel.ls.representation.json;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONObject implements JSONValue {

    private List<String> names;
    private List<JSONValue> values;

    public JSONObject(){
        names = new ArrayList<>();
        values = new ArrayList<>();

    }

    public JSONObject add(String name, int value) {
        add(name, new JSONNumber(Integer.toString(value)));
        return this;
    }

    public JSONObject add(String name, String value) {
        if (value != null)
            add(name,new JSONString(value));
        return this;
    }


    public JSONObject add(String name, boolean value) {
        add(name, new JSONBoolean(value));
        return this;
    }


    public JSONObject add(String name, Date value) {
        if(value != null)
            add(name, new JSONDueDate(String.valueOf(value)));
        return this;
    }

    public JSONObject add(String name, JSONValue value) {
        if(value != null) {
            names.add(name);
            values.add(value);
        }
        return this;
    }

    public void toFile(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        JSONWriter writer = write(new JSONWriter());
        fileWriter.append(writer.toString());
        fileWriter.close();
    }

    public List<String> getNames() {
        return names;
    }

    @Override
    public JSONWriter write(JSONWriter writer) throws IOException {
        writer.writeObjectOpen();

        Iterator<String> namesIterator = names.iterator();
        Iterator<JSONValue> valuesIterator = values.iterator();

        if(namesIterator.hasNext()) {
            writer.writeString(namesIterator.next());
            writer.writeMemberSeparator();
            valuesIterator.next().write(writer);
            while (namesIterator.hasNext()) {
                writer.writeSeparator();
                writer.writeString(namesIterator.next());
                writer.writeMemberSeparator();
                valuesIterator.next().write(writer);
            }
        }
        writer.writeObjectClose();

        return writer;
    }

    @Override
    public String toString() {

        String res = null;
        JSONWriter jsonWriter = new JSONWriter();
        try {
            JSONWriter write = write(jsonWriter);
            res = write.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }
}
