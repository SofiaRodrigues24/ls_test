package pt.isel.ls.commands;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.TreeUtilsTest;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.manager.Result;
import pt.isel.ls.manager.Tree;

import java.sql.SQLException;

public class ChecklistsTest {
    static Tree tree;

    @BeforeClass
    public static void init() {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }

    /**
     * test POST /checklists and GET /checklists/{cid}
     * @throws SQLException
     */
    @Test
    public void postchecklists_and_getchecklists() throws SQLException {
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"POST", "/checklists/description=tasks+of+the+project&name=ls+project"});
        Command command = tree.search(rq);

        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        Integer id = result.getResult();

        dbConnection.disconnect();

        String str = "/checklists/"+id;
        DBConnection dbConnection1 = new DBConnection(new SQLServerDataSource());
        Request rq1 = new Request(new String[]{"GET", str});
        Command command1 = tree.search(rq1);

        Result<CheckList> result1 = command1.execute(dbConnection1.getConnection(), rq1.getParameters());
        CheckList checkList = result1.getResult();


        //Assert
        assertNotNull(rq);
        assertNotNull(command);
        assertNotNull(result);

        assertEquals(2, rq.getParameters().size()); //name and description
        assertEquals("POST", rq.getMethod());

        assertNotNull(rq1);
        assertNotNull(command);
        assertEquals(id.intValue(), checkList.getCid());

    }

}
