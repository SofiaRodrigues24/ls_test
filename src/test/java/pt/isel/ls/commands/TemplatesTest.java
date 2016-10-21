package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.TreeUtilsTest;
import pt.isel.ls.domain.Template;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.manager.Result;
import pt.isel.ls.manager.Tree;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TemplatesTest {
    static Tree tree;

    @BeforeClass
    public static void init() {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }

    /**
     * test POST /templates and GET /templates/{tid}
     * @throws SQLException
     */
    @Test
    public void posttemplates_and_gettemplatestid() throws SQLException {
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"POST", "/templates/description=tasks+of+the+project&name=ls+project"});
        Command command = tree.search(rq);

        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        Integer id = result.getResult();

        dbConnection.disconnect();

        String str = "/templates/"+id;
        DBConnection dbConnection1 = new DBConnection(new SQLServerDataSource());
        Request rq1 = new Request(new String[]{"GET", str});
        Command command1 = tree.search(rq1);

        Result<Template> result1 = command1.execute(dbConnection1.getConnection(), rq1.getParameters());
        Template template = result1.getResult();

        dbConnection1.disconnect();

        //Assert
        assertNotNull(rq);
        assertNotNull(command);
        assertNotNull(result);

        assertEquals(2, rq.getParameters().size()); //name and description
        assertEquals("POST", rq.getMethod());

        assertNotNull(rq1);
        assertNotNull(command);
        assertEquals(id.intValue(), template.getTid());

    }
}
