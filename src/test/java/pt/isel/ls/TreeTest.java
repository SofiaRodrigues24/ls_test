package pt.isel.ls;

import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Request;
import pt.isel.ls.tree.Tree;

import java.util.HashMap;

import static org.junit.Assert.*;

public class TreeTest {

    static Tree tree;

    @BeforeClass
    public static void init() {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }


    @Test
    public void search_for_a_command() {
        //Arrange
        String args = "POST /checklists/description=ls+project&name=ls";
        Request rq = new Request(args);
        Command command = tree.search(rq);

        //Act
        String method = rq.getMethod();
        String path = rq.getPath();
        HashMap<String, String> parameters = rq.getParameters();

        //Assert
        assertNotNull(method);
        assertNotNull(path);
        assertNotNull(parameters);
        assertNotNull(command);

        assertEquals(2,parameters.size());
        assertEquals("ls", parameters.get("name"));
        assertEquals("ls project", parameters.get("description"));

    }


    @Test
    public void nonexistent_command() {
        //Arrange
        String args = "POST /tasks/description=ls+project&name=ls";
        Request rq = new Request(args);
        Command command = tree.search(rq);

        assertNotNull(rq);
        assertNull(command);
    }
}
