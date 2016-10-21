package pt.isel.ls;

import org.junit.Test;
import pt.isel.ls.manager.Request;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestTest {

    @Test
    public void test() {
        //Arrange
        String [] args = {"POST", "/checklists/description=ls+project&name=ls"};
        Request rq = new Request(args);

        //Act
        String method = rq.getMethod();
        String path = rq.getPath();
        HashMap<String, String> parameters = rq.getParameters();

        //Assert
        assertNotNull(method);
        assertNotNull(path);
        assertNotNull(parameters);

        assertEquals(2,parameters.size());
        assertEquals("ls", parameters.get("name"));
        assertEquals("ls project", parameters.get("description"));


    }
}
