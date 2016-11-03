package pt.isel.ls.representation.json;

import java.io.IOException;

public interface JSONValue {
    JSONWriter write(JSONWriter writer) throws IOException;
}
