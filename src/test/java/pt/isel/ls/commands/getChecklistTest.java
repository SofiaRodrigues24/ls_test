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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class getChecklistTest {

    static Tree tree;

    @BeforeClass
    public static void init(){
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }

    @Test
    public void getChecklistTest() throws SQLException {


        DBConnection db = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"GET", "/checklists"});
        Connection con = db.getConnection();
        Command command = tree.search(rq);

        Result<ArrayList<CheckList>> result = command.execute(con,rq.getParameters());

        ArrayList<CheckList> checkLists = result.getResult();

        String query = "select * from checklist";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            ArrayList<Integer> cids = new ArrayList<>();
            while (rs.next()){
                cids.add(rs.getInt("cid"));
            }

            assertEquals(cids.size(), checkLists.size());
        }

    }
}
