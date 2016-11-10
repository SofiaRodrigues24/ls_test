package pt.isel.ls.representation.json;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

public class JSONObject implements JSONValue {


    private List<String> names;
    private List<JSONValue> values;

    private HashMap<String, JSONValue> map;


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

    public void writer(String filename) throws IOException {
        JSONWriter write = this.write(new JSONWriter(new BufferedWriter(new FileWriter(filename))));
        write.close();
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<JSONValue> getValues() {
        return values;
    }

    public void setValues(List<JSONValue> values) {
        this.values = values;
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
        JSONWriter writer = new JSONWriter();
        String res = "";

        try {
            res += writer.writeObjectOpen();
            Iterator<String> namesIterator = names.iterator();
            Iterator<JSONValue> valuesIterator = values.iterator();

            if(namesIterator.hasNext()) {
                res+= writer.writeString(namesIterator.next().toString());
                res += writer.writeMemberSeparator();
                res += valuesIterator.next().toString();
                while (namesIterator.hasNext()) {
                    res += writer.writeSeparator();
                    res += writer.writeString(namesIterator.next().toString());
                    res+= writer.writeMemberSeparator();
                    res += valuesIterator.next().toString();
                }
            }
            res += writer.writeObjectClose();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return res;
    }
}
