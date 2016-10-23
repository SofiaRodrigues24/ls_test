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

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class getChacklistOpenSortedDuedateTest {
    static Tree tree;

    @BeforeClass
    public static void init(){
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }


    @Test
    public void getChacklistOpenSortedDuedateTest() throws SQLException {

        String str = "/checklists/open/sorted/duedate";
        DBConnection db = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"GET", str});
        Command command = tree.search(rq);
        Connection con = db.getConnection();
        Result<ArrayList<CheckList>> result1 = command.execute(con, rq.getParameters());
        ArrayList<CheckList> checkList = result1.getResult();

        String sql = "select * from checklist where completed = 0 order by check_duedate asc";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        ArrayList<Date> dates = new ArrayList<>();
        while(rs.next()){
            dates.add(rs.getDate("check_duedate"));
        }
        int i = 0;
        for(CheckList c : checkList) {
            Date check = dates.get(i++);
            assertEquals(c.getDuedate(),check );
        }


    }
}
