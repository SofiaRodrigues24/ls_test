package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.TreeUtilsTest;
import pt.isel.ls.domain.Task;
import pt.isel.ls.domain.Template;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.manager.Result;
import pt.isel.ls.manager.Tree;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TemplatesTest {
    static Tree tree;

    @BeforeClass
    public static void init() {
        tree = new Tree();
        TreeUtilsTest.initTree(tree);
    }

    /**
     * POST/templates, POST/templates/{tid}/tasks and GET/templates/{tid}
     * @throws SQLException
     */
    @Test
    public void templates_test() throws SQLException {
        Result<Integer> tid = postTemplates();
        Result<Integer> lid = postTemplatesTidTasks(tid.getResult());
        Result<Template> template = getTemplatesTid(tid.getResult());

        Template result = template.getResult();
        List<Task> templateTasks = result.getTemplateTasks();

        //Assert
        assertNotNull(tid);
        assertNotNull(lid);
        assertNotNull(template);

        assertEquals(tid.getResult(), (Integer)result.getTid());
        assertEquals(1, templateTasks.size());
        assertEquals(lid.getResult(), (Integer)templateTasks.get(0).getLid());

    }

    private Result<Template> getTemplatesTid(Integer tid) throws SQLException {

        String str = "/templates/"+tid;
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"GET", str});
        Command command = tree.search(rq);

        Result<Template> result = command.execute(dbConnection.getConnection(), rq.getParameters());

        dbConnection.disconnect();
        return result;
    }

    private Result<Integer> postTemplatesTidTasks(Integer tid) throws SQLException {
        String str = "/templates/"+tid+"/tasks/name=git&description=commit+the+changes";
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"POST", str});
        Command command = tree.search(rq);

        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());
        dbConnection.disconnect();

        return result;
    }

    private Result<Integer> postTemplates() throws SQLException {
        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());
        Request rq = new Request(new String[]{"POST", "/templates/description=project+taskst&name=ls+project"});
        Command command = tree.search(rq);

        Result<Integer> result = command.execute(dbConnection.getConnection(), rq.getParameters());

        dbConnection.disconnect();

        return result;
    }
}
