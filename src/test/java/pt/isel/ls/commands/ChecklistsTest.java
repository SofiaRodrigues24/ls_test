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
import java.util.ArrayList;
import java.util.List;

public class ChecklistsTest {
    static Tree tree;

    static Result<Integer> cid, cid1, cid2, cid3, cid4;

    @BeforeClass
    public static void init() throws SQLException {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);

        cid = postChecklists("tests", "ls+project", null);
        cid1 = postChecklists("tests1", "checklist+test", "2016-10-27");
        cid2 = postChecklists("tests2", "checklist+test", "2016-10-23");
        cid3 = postChecklists("tests3", "checklist+test", "2016-11-25");
        cid4 = postChecklists("tests4", "checklist+test", "2016-09-24");
    }
    /**
     * POST/checklists/{cid}/tasks,
     * POST/checklists/{cid}/tasks/{lid} and GET/checklists/{cid}
     * @throws SQLException
     */
    @Test
    public void checklists_test() throws SQLException {
        Result<Integer> lid = postChecklistsCidTasks(cid.getResult(), "task+check", "task+check+test");
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

    /**
     * GET /checklists
     * @throws SQLException
     */
    @Test
    public void get_checklists_test() throws SQLException {
        Result<List<CheckList>> result = getCheckLists();

        List<CheckList> result1 = result.getResult();
        List<CheckList> checkLists = new ArrayList<>();

        checkLists.add(new CheckList(cid.getResult() ,"tests", "ls project"));
        checkLists.add(new CheckList(cid1.getResult() ,"tests1", "checklist test"));
        checkLists.add(new CheckList(cid2.getResult() ,"tests2", "checklist test"));
        checkLists.add(new CheckList(cid3.getResult() ,"tests3", "checklist test"));
        checkLists.add(new CheckList(cid4.getResult() ,"tests4", "checklist test"));


        //Assert
        assertNotNull(result);
        assertEquals(5, result1.size());
        for (int i = 0; i < checkLists.size(); i++) {
            assertEquals(checkLists.get(i).getCid(), result1.get(i).getCid());
            assertEquals(checkLists.get(i).getName(), result1.get(i).getName());
            assertEquals(checkLists.get(i).getDescription(), result1.get(i).getDescription());
        }
    }


    /**
     * GET /checklists/open/sorted/duedate
     * @throws SQLException
     */
    @Test
    public void get_checklists_open_sorted_duedate_test() throws SQLException {
        String [] dates = new String[]{null, "2016-09-24", "2016-10-23", "2016-10-27", "2016-11-25"};

        Result<List<CheckList>> result = getCheckListsOpenSortedDuedate();
        List<CheckList> checkLists = result.getResult();

        //Assert
        assertNotNull(result);

        for (int i = 1; i < checkLists.size(); i++) {
            assertEquals(dates[i], checkLists.get(i).getDuedate().toString());
        }
    }


   /* auxiliary methods */
    private static Result<List<CheckList>> getCheckLists() throws SQLException {
        String str = "/checklists";
        Request rq = new Request(new String[]{"GET", str});
        Command command = tree.search(rq);

        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Result<List<CheckList>> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();

        return result;
    }

     private static Result<CheckList> getChecklistsCid(Integer cid) throws SQLException {
         String str = "/checklists/"+cid;
         Request rq = new Request(new String[]{"GET", str});
         Command command = tree.search(rq);

         DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
         Result<CheckList> result = command.execute(dbConnection.getConnection(), rq.getParameters());
         dbConnection.disconnect();

         return result;

    }

    private static Result<List<CheckList>> getCheckListsOpenSortedDuedate() throws SQLException {
        String str = "/checklists/open/sorted/duedate";
        Request rq = new Request(new String[]{"GET", str});
        Command command = tree.search(rq);

        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Result<List<CheckList>> checkLists = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();

        return checkLists;
    }

    private static Result<Integer> postChecklists(String name, String description, String duedate) throws SQLException {
        String str = "/checklists/description="+description+"&name="+name+(duedate!=null?"&duedate="+duedate:"");
        Request rq = new Request(new String[]{"POST", str});
        Command command = tree.search(rq);

        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();

        return result;
    }

    private static Result<Integer> postChecklistsCidTasks(Integer cid, String name, String description) throws SQLException {
        String str = "/checklists/"+cid+"/tasks/name="+name+"&description="+description;
        Request rq = new Request(new String[]{"POST", str});
        Command command = tree.search(rq);

        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();

        return result;
    }

    private static Result<Integer> postChecklistsCidTasksLid(Integer cid, Integer lid) throws SQLException {
        String str = "/checklists/"+cid+"/tasks/"+lid+"/isClosed=true";
        Request rq = new Request(new String[]{"POST", str});
        Command command = tree.search(rq);

        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();

        return result;
    }
}
