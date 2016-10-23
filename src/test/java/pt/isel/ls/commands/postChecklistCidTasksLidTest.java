package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.TreeUtilsTest;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.manager.Tree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by tiago on 22-Oct-16.
 */
public class postChecklistCidTasksLidTest{

    static Tree tree;

    @BeforeClass
    public static void init() {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }
    @Test
    public void postChecklistCidTasksLidTest() throws SQLException {
        DBConnection db = new DBConnection(new SQLServerDataSource());
        int cid = 1;
        int lid = 1;
        Request rq = new Request(new String[]{"POST", "/checklist/{"+cid+"}/tasks/{"+lid+"}"});

        Command command = tree.search(rq);
        Connection con = db.getConnection();
        String sql = "SELECT * FROM task_check where cid = ? and lid = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1,cid);
        ps.setInt(2,lid);

        ResultSet rs = ps.executeQuery();

        command.execute(con, rq.getParameters());
        String sql2 = "SELECT * FROM task_check where cid = ? and lid = ?";
        PreparedStatement ps2 = con.prepareStatement(sql2);

        ps2.setInt(1,cid);
        ps2.setInt(2,lid);

        ResultSet rs2 = ps.executeQuery();

        while(rs.next() && rs2.next()){
            assertEquals(rs.getBoolean("isclosed"), !rs2.getBoolean("isclosed"));
        }

    }
}
