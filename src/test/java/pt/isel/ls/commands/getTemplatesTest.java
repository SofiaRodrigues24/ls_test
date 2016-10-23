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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by tiago on 22-Oct-16.
 */
public class getTemplatesTest {
    static Tree tree;

    @BeforeClass
    public static void init() {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }
    @Test
    public void getTemplateTest() throws SQLException {
        DBConnection db = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"GET", "/templates"});

        Command command = tree.search(rq);
        Connection con = db.getConnection();
        Result<ArrayList<Template>> result = command.execute(con, rq.getParameters());

        ArrayList<Template> templates = result.getResult();

        String sql = "SELECT * FROM template";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet resultSet = ps.executeQuery();
        ArrayList<Integer> tids = new ArrayList<>();
        while(resultSet.next()) {
            tids.add(resultSet.getInt("tid"));
        }

        assertEquals(tids.size(),templates.size());
    }

}
