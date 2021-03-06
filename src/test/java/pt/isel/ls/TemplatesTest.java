package pt.isel.ls;

import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.CommandManager;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.domain.Task;
import pt.isel.ls.domain.Template;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.domain.Result;
import pt.isel.ls.tree.Tree;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SuppressWarnings("unchecked")
public class TemplatesTest {
    static Tree tree;

    static Result<Integer> tid, tid1, tid2, tid3, tid4;

    @BeforeClass
    public static void init() throws Exception {
        DBConnection.init("LS_DBCONN_TEST_SQLSRV");

        tree = new Tree();
        TreeUtilsTest.initTree(tree);

        tid = postTemplates("test", "template+test");
        tid1 = postTemplates("test1", "template+test");
        tid2 = postTemplates("test2", "template+test");
        tid3 = postTemplates("test3", "template+test");
        tid4 = postTemplates("test4", "template+test");
    }

    /**
     * POST/templates/{tid}/tasks and GET/templates/{tid}
     * @throws SQLException
     */
    @Test
    public void templates_test() throws Exception {
        Result<Integer> lid = postTemplatesTidTasks(tid.getResult(), "task+template", "task+template+test");
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

    /**
     * GET /templates
     * @throws SQLException
     */
    @Test
    public void get_templates_test() throws Exception {
        Result<Collections<Template>> result = getTemplates();

        List<Template> result1 = (List<Template>) result.getResult().getList();
        List<Template> templates = new ArrayList<>();

        templates.add(new Template(tid.getResult() ,"test", "template test"));
        templates.add(new Template(tid1.getResult() ,"test1", "template test"));
        templates.add(new Template(tid2.getResult() ,"test2", "template test"));
        templates.add(new Template(tid3.getResult() ,"test3", "template test"));
        templates.add(new Template(tid4.getResult() ,"test4", "template test"));


        //Assert
        assertNotNull(result);
        assertEquals(5, result1.size());

        for (int i = 0; i < templates.size(); i++) {
            assertEquals(templates.get(i).getTid(), result1.get(i).getTid());
            assertEquals(templates.get(i).getName(), result1.get(i).getName());
            assertEquals(templates.get(i).getDescription(), result1.get(i).getDescription());
        }
    }

    /**
     * POST /templates/tid/create
     * @throws SQLException
     */
    @Test
    public void post_templates_tid_create_test() throws Exception {
        Result<Integer> li1 = postTemplatesTidTasks(tid1.getResult(), "task+template1", "task+template+test");
        Result<Integer> li2 = postTemplatesTidTasks(tid1.getResult(), "task+template2", "task+template+test");
        Result<Integer> li3 = postTemplatesTidTasks(tid1.getResult(), "task+template3", "task+template+test");

        Result<Template> templatesTid = getTemplatesTid(tid1.getResult());
        Result<Integer> result = postTemplatesTidCreate(tid1.getResult());

        CheckList checkList = getChecklistsCid(result.getResult());
        Template template = templatesTid.getResult();

        List<Task> templateTasks = template.getTemplateTasks(),
                checkListTasks = checkList.getTasks();


        //Assert
        assertNotNull(result);
        assertNotNull(checkList);

        assertEquals(3, checkListTasks.size());
        assertEquals(templateTasks.size(), checkListTasks.size());

        assertEquals(template.getName(), checkList.getName());
        assertEquals(template.getDescription(), checkList.getDescription());

        for (int i = 0; i < checkList.getTasks().size(); i++) {
            assertEquals(templateTasks.get(i).getLid(), checkListTasks.get(i).getLid());
            assertEquals(templateTasks.get(i).getName(), checkListTasks.get(i).getName());
            assertEquals(templateTasks.get(i).getDescription(), checkListTasks.get(i).getDescription());
        }
    }

    /* * ** auxiliary methods* * */

    private static CheckList getChecklistsCid(Integer cid) throws Exception {
        String str = "/checklists/"+cid + " accept:text/plain";
        Request rq = new Request("GET "+ str);
        Command command = tree.search(rq);

        Result<CheckList> result = (Result<CheckList>) command.execute(new CommandManager(rq));

        return result.getResult();
    }

    private static Result<Collections<Template>> getTemplates() throws Exception {
        String str = "/templates accept:text/plain";
        Request rq = new Request("GET "+ str);
        Command command = tree.search(rq);

        return  (Result<Collections<Template>>) command.execute(new CommandManager(rq));
    }

    private static Result<Template> getTemplatesTid(Integer tid) throws Exception {
        String str = "/templates/"+tid +" accept:text/plain";
        Request rq = new Request("GET "+ str);
        Command command = tree.search(rq);

        return (Result<Template>)command.execute(new CommandManager(rq));
    }


    private static Result<Integer> postTemplates(String name, String description) throws Exception {
        String str = "/templates/description="+description+"&name="+name;
        Request rq = new Request("POST "+ str);
        Command command = tree.search(rq);

        return  (Result<Integer>)command.execute(new CommandManager(rq));

    }

    private static Result<Integer> postTemplatesTidCreate(Integer tid) throws Exception {
        String str = "/templates/"+tid+"/create";
        Request rq = new Request("POST " + str);
        Command command = tree.search(rq);

        return  (Result<Integer>)command.execute(new CommandManager(rq));
    }

    private static Result<Integer> postTemplatesTidTasks(Integer tid, String name, String description) throws Exception {
        String str = "/templates/"+tid+"/tasks/name="+name+"&description="+description;
        Request rq = new Request("POST "+ str);
        Command command = tree.search(rq);

        return  (Result<Integer>)command.execute(new CommandManager(rq));
    }
}
