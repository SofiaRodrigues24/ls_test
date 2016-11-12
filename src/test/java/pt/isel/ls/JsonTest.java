package pt.isel.ls;


import org.junit.Test;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.domain.Task;
import pt.isel.ls.domain.Template;
import pt.isel.ls.representation.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonTest {

    @Test
    public void getJsonObjectFromChecklist() throws IOException {
        CheckList c = new CheckList(1, "json", "jsonTest");
        JSONObject jsonObject = c.getJsonObject();
        String s = jsonObject.toString();
        String exp = "{\"class\":[checklist],\"properties\":{\"cid\":1,\"name\":json,\"description\":jsonTest,\"isClosed\":false}}";

        List<String> names = jsonObject.getNames();

        assertEquals(names.get(0), "class");
        assertEquals(names.get(1), "properties");

        assertNotNull(jsonObject);
        assertNotNull(s);
        assertEquals(exp, s);

    }

    @Test
    public void getJsonObjectFromTemplate() throws IOException {
        Template t = new Template(1, "json", "jsonTest");
        JSONObject jsonObject = t.getJsonObject();
        String s = jsonObject.toString();
        String exp = "{\"class\":[template],\"properties\":{\"tid\":1,\"name\":json,\"description\":jsonTest}}";

        List<String> names = jsonObject.getNames();

        assertEquals(names.get(0), "class");
        assertEquals(names.get(1), "properties");

        assertNotNull(jsonObject);
        assertNotNull(s);
        assertEquals(exp, s);


    }

    @Test
    public void getJsonObjectFromTask() throws IOException {
        Task t = new Task(1, "json", "jsonTest");
        JSONObject jsonObject = t.getJsonObject();
        String s = jsonObject.toString();
        String exp = "{\"class\":[task],\"properties\":{\"lid\":1,\"name\":json,\"description\":jsonTest,\"isClosed\":false}}";

        assertEquals(exp, s);
    }

    @Test
    public void getJsonObjectFromCollection() throws IOException {
        CheckList c1 = new CheckList(1, "test1", "jsonTest1");
        CheckList c2 = new CheckList(2, "test2", "jsonTest2");

        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(c1);
        checkLists.add(c2);

        Collections<CheckList> list = new Collections<>("checklist", checkLists);

        JSONObject jsonObject = list.getJsonObject();
        String s = jsonObject.toString();

        String exp = "{\"class\":[checklist,collection],\"properties\":{\"count\":2}," +
                "\"entities\":[" +
                "{\"class\":[checklist],\"properties\":{\"cid\":1,\"name\":test1,\"description\":jsonTest1,\"isClosed\":false}}," +
                "{\"class\":[checklist],\"properties\":{\"cid\":2,\"name\":test2,\"description\":jsonTest2,\"isClosed\":false}}]}";

        List<String> names = jsonObject.getNames();

        assertEquals("class", names.get(0));
        assertEquals("properties", names.get(1));
        assertEquals("entities", names.get(2));

        assertEquals(exp, s);


    }


}
