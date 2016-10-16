package pt.isel.ls;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import pt.isel.ls.commands.*;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.*;

import java.util.HashMap;

public class App {

    public static void main(String[] args) throws Exception {
        HashMap<String, TreeNode> hashMap = new HashMap<>();
        Tree tree = new Tree(hashMap);
        setup(tree);

        Request rq = new Request(args);

        Command command = tree.search(rq);

        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());

        Result result = command.execute(dbConnection.getConnection(), rq.getParameters());

        dbConnection.disconnect();

        result.print();
    }

    private static void setup(Tree tree) {
        for (int i = 0; i < Data.templates.length; i++) {
            tree.insert(Data.templates[i],Data.commands[i]);
        }
    }
}
