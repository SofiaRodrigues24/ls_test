package pt.isel.ls;


import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import pt.isel.ls.commands.*;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.*;

public class App {

    //java -cp "vendor/main/*;build/classes/main" pt.isel.ls.App GET /checklists
    public static void main(String[] args) {
        Tree tree = new Tree();
        setup(tree);

        Request rq = new Request(args);

        Command command = tree.search(rq);

        DBConnection dbConnection = new DBConnection(new SQLServerDataSource());

        Result result = null;
        try {
            result = command.execute(dbConnection.getConnection(), rq.getParameters());
        } catch (Exception e) {
            System.out.println("error - connection");
        }finally {
            try {
                dbConnection.disconnect();
            } catch (Exception e) {
                System.out.println("error - disconnect");
            }
        }
        result.print();
    }

    private static void setup(Tree tree) {
        for (int i = 0; i < Data.templates.length; i++) {
            tree.insert(Data.templates[i],Data.commands[i]);
        }
    }
}
