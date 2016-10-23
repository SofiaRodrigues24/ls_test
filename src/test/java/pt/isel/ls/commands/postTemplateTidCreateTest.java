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

import static org.junit.Assert.assertTrue;

/**
 * Created by tiago on 22-Oct-16.
 */
public class postTemplateTidCreateTest {
    static Tree tree;

    @BeforeClass
    public static void init() {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }

    @Test
    public void postTemplateTidCreateTest() throws SQLException {
        DBConnection db = new DBConnection(new SQLServerDataSource());
        int tid = 10;
        Request rq = new Request(new String[]{"POST", "/templates/{"+tid+"}/create","description=create+new+checklist&name=checklist+test"});
        Command command = tree.search(rq);
        Connection con = db.getConnection();

        PreparedStatement ps = con.prepareStatement("SELECT * FROM checklist where tid = ?");
        ps.setInt(1,tid);

        ResultSet rs = ps.executeQuery();

        int countCheck = 1;
        while(rs.next()){
            countCheck++;
        }

        command.execute(con, rq.getParameters());

        ps.close();
        ps = con.prepareStatement("SELECT * FROM checklist where tid = ?");
        ps.setInt(1,tid);

        ResultSet rs2 = ps.executeQuery();

        int countCheck2 = 1;
        while(rs2.next()){
            countCheck++;
        }
        assertTrue(countCheck2>countCheck);

    }
}
