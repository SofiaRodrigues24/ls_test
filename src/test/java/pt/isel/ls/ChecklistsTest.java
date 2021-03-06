package pt.isel.ls;


import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.CommandManager;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.domain.Tag;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.domain.Result;
import pt.isel.ls.tree.Tree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class ChecklistsTest {
    static Tree tree;

    static Result<Integer> cid, cid1, cid2, cid3, cid4;

    @BeforeClass
    public static void init() throws Exception {

        DBConnection.init("LS_DBCONN_TEST_SQLSRV");

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
    public void checklists_test() throws Exception {
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
    public void get_checklists_test() throws Exception {
        Result<Collections<CheckList>> result = getCheckLists();

        List<CheckList> result1 = (List<CheckList>) result.getResult().getList();
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
    public void get_checklists_open_sorted_duedate_test() throws Exception {
        String [] dates = new String[]{null, "2016-09-24", "2016-10-23", "2016-10-27", "2016-11-25"};

        Result<Collections<CheckList>> result = getCheckListsOpenSortedDuedate();
        List<CheckList> checkLists = (List<CheckList>)result.getResult().getList();

        //Assert
        assertNotNull(result);

        for (int i = 1; i < checkLists.size(); i++) {
            assertEquals(dates[i], checkLists.get(i).getDuedate().toString());
        }
    }

    /**
     * POST /tags, POST /checklists{cid}/tags
     */
    @Test
    public void post_tag_test() throws Exception {
        Result<Integer> tag1 = postTags("tag1", 1);
        Result<Integer> tag2 = postTags("tag2", 3);
        Result<Integer> cid = postChecklistsCidTags(cid2.getResult(), tag1.getResult());
        Result<Integer> cid1 = postChecklistsCidTags(cid2.getResult(), tag2.getResult());

        Result<CheckList> result = getChecklistsCid(cid.getResult());

        CheckList checklist = result.getResult();
        List<Tag> tags = checklist.getTags();

        assertEquals(cid2.getResult(), cid.getResult());
        assertEquals(cid2.getResult(), cid1.getResult());

        assertEquals(2, tags.size());

        assertEquals("tag1", tags.get(0).getName());
        assertEquals(tag1.getResult().intValue(), tags.get(0).getGid());
        assertEquals(1, tags.get(0).getColor());

        assertEquals("tag2", tags.get(1).getName());
        assertEquals(tag2.getResult().intValue(), tags.get(1).getGid());
        assertEquals(3, tags.get(1).getColor());

    }

    /* auxiliary methods */
    private Result<Integer> postChecklistsCidTags(Integer cid, Integer gid) throws Exception {
        String str = "/checklists/"+cid+"/tags/gid="+gid;
        Request rq = new Request("POST "+str);
        Command command = tree.search(rq);

        return (Result<Integer>)command.execute(new CommandManager(rq));
    }


    private Result<Integer> postTags(String name, int color) throws Exception {
        String str = "/tags/name="+name+"&color="+color;
        Request rq = new Request("POST "+str);
        Command command = tree.search(rq);

        return  (Result<Integer>)command.execute(new CommandManager(rq));
    }


    private static Result<Collections<CheckList>> getCheckLists() throws Exception {
        String str = "/checklists accept:text/plain";
        Request rq = new Request("GET " + str);
        Command command = tree.search(rq);

        return  (Result<Collections<CheckList>>) command.execute(new CommandManager(rq));
    }



     private static Result<CheckList> getChecklistsCid(Integer cid) throws Exception {
         String str = "/checklists/"+cid +" accept:text/plain";
         Request rq = new Request("GET "+ str);
         Command command = tree.search(rq);

        return  (Result<CheckList>) command.execute(new CommandManager(rq));
    }

    private static Result<Collections<CheckList>> getCheckListsOpenSortedDuedate() throws Exception {
        String str = "/checklists/open/sorted/duedate accept:text/plain";
        Request rq = new Request("GET "+ str);
        Command command = tree.search(rq);

        return  (Result<Collections<CheckList>>) command.execute(new CommandManager(rq));
    }

    private static Result<Integer> postChecklists(String name, String description, String duedate) throws Exception {
        String str = "/checklists/description="+description+"&name="+name+(duedate!=null?"&duedate="+duedate:"");
        Request rq = new Request("POST "+ str);
        Command command = tree.search(rq);

        return (Result<Integer>)command.execute(new CommandManager(rq));
    }

    private static Result<Integer> postChecklistsCidTasks(Integer cid, String name, String description) throws Exception {
        String str = "/checklists/"+cid+"/tasks/name="+name+"&description="+description;
        Request rq = new Request("POST " +str);
        Command command = tree.search(rq);

        return (Result<Integer>)command.execute(new CommandManager(rq));
    }

    private static Result<Integer> postChecklistsCidTasksLid(Integer cid, Integer lid) throws Exception {
        String str = "/checklists/"+cid+"/tasks/"+lid+"/isClosed=true";
        Request rq = new Request("POST "+ str);
        Command command = tree.search(rq);

        return (Result<Integer>)command.execute(new CommandManager(rq));
    }
}
