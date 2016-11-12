package pt.isel.ls;


import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.CommandManager;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.domain.Result;
import pt.isel.ls.tree.Tree;

import java.io.IOException;
import java.util.Scanner;

public class App {

    //java -cp "vendor/main/*;build/classes/main" pt.isel.ls.App GET /checklists
    public static void main(String[] args) throws IOException {
       DBConnection.init("LS_DBCONN_APP_SQLSRV");

        Tree tree = new Tree();
        setup(tree);

        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Choose a command: ");

            String s = in.nextLine();
            Request rq = new Request(s);
            Command command = tree.search(rq);
            CommandManager cmdManager = new CommandManager(rq);

            try {

                Result result = command.execute(cmdManager);
                result.consumer(cmdManager);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static void setup(Tree tree) {
        for (int i = 0; i < Data.templates.length; i++) {
            tree.insert(Data.templates[i],Data.commands[i]);
        }
    }
}
