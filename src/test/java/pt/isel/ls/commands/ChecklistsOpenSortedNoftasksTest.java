package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
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

import static org.junit.Assert.assertTrue;

/**
 * Created by sofia on 21/10/2016.
 */
public class ChecklistsOpenSortedNoftasksTest {
    static Tree tree;

    @BeforeClass
    public static void init() {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }


    @Test
    public void getChecklistsOpenSortedNoftasksTest() throws SQLException {

        String str = "/checklists/open/sorted/noftasks";
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"GET", str});
        Command command = tree.search(rq);

        Result<ArrayList<CheckList>> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        ArrayList<CheckList> checkList = result.getResult();
        int count = Integer.MAX_VALUE;
        for(CheckList c : checkList){
            assertTrue(count>c.getNumberOfTasks());
            count = c.getNumberOfTasks();
        }


    }
}
