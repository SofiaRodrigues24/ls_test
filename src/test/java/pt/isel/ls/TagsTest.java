package pt.isel.ls;

import org.junit.BeforeClass;
import pt.isel.ls.domain.*;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.tree.Tree;

import org.junit.BeforeClass;
import org.junit.Test;
import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.CommandManager;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.domain.Result;
import pt.isel.ls.tree.Tree;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * Created by Sonia on 20-11-2016.
 */
public class TagsTest {
    static Tree tree;

    static Result<Integer> gid, gid1, gid2, gid3, gid4;

    @BeforeClass
    public static void init() throws Exception {
        DBConnection.init("LS_DBCONN_TEST_SQLSRV");

        tree = new Tree();
        TreeUtilsTest.initTree(tree);

        gid = postTags("tag", 0);
        gid1 = postTags("tag1", 1);
        gid2 = postTags("tag2", 2);
        gid3 = postTags("tag3", 3);
        gid4 = postTags("tag4", 4);

    }
    private static Result<Integer> postTags(String name, int color) throws Exception {
        String str = "/tags/name="+name+"&color="+color;
        Request rq = new Request("POST "+ str);
        Command command = tree.search(rq);
        return  (Result<Integer>)command.execute(new CommandManager(rq));
    }
    protected Result<Tag> deleteTags(Integer gid)throws Exception{
        String str ="/tags/"+gid;
        Request rq = new Request("DELETE" +str);
        Command command = tree.search(rq);
        return (Result<Tag>)command.execute(new CommandManager(rq));
    }
    @Test
    public void post_tags_test()throws Exception{
        Result<Integer> gid = postTags("tag",0);
        Result<Integer> gid1 =postTags("tag1",1);
        Result<Integer> gid2 =postTags("tag2",2);
        Result<Integer> gid3 =postTags("tag3",3);

        List<Tag> tag;




    }
    @Test
    public void delete_tags_gid_test(){

    }
}
