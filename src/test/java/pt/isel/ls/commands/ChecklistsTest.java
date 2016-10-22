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
     * POST/checklists, POST/checklists/{cid}/tasks,
     * POST/checklists/{cid}/tasks/{lid} and GET/checklists/{cid}
     * @throws SQLException
     */
    @Test
    public void postchecklists_and_getchecklists() throws SQLException {
        Result<Integer> cid = postChecklists();
        Result<Integer> lid = postChecklistsCidTasks(cid.getResult());
        Result<Integer> lid1 = postChecklistsCidTasksLid(cid.getResult(), lid.getResult());
        Result<CheckList> result = getChecklistsCid(cid.getResult());

        CheckList checkList = result.getResult();


        //Assert
        assertNotNull(cid);
        assertNotNull(lid);
        assertNotNull(result);

        assertEquals(lid.getResult(), lid1.getResult());
        assertEquals(1, checkList.getTasks().size());
        assertEquals(lid.getResult(), (Integer)checkList.getTasks().get(0).getLid());
        assertTrue(checkList.getTasks().get(0).isClosed());

    }

    private Result<CheckList> getChecklistsCid(Integer cid) throws SQLException {
        String str = "/checklists/"+cid;
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"GET", str});
        Command command = tree.search(rq);

        Result<CheckList> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();
        return result;

    }

    private Result<Integer> postChecklistsCidTasksLid(Integer cid, Integer lid) throws SQLException {
        String str = "/checklists/"+cid+"/tasks/"+lid+"/isClosed=true";
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"POST", str});
        Command command = tree.search(rq);

        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();

        return result;
    }

    private Result<Integer> postChecklistsCidTasks(Integer cid) throws SQLException {
        String str = "/checklists/"+cid+"/tasks/name=task&description=buy+milk";
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"POST", str});
        Command command = tree.search(rq);

        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();

        return result;
    }

    private Result<Integer> postChecklists() throws SQLException {
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"POST", "/checklists/description=project+tasks&name=ls+project"});
        Command command = tree.search(rq);

        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());

        dbConnection.disconnect();

        return result;
    }

}
