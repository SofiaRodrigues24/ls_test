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
        boolean isIterative = args.length == 0;

        String arguments = "";

        do {
			System.out.println("Choose a command ");
            arguments = isIterative?in.nextLine():toString(args);
            Request rq = new Request(arguments);
            Command command = tree.search(rq);
            CommandManager cmdManager = new CommandManager(rq);

            try {

                Result result = command.execute(cmdManager);
                result.consumer(cmdManager);
                System.out.println("------ ------ ------");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while(isIterative);
    }

    private static String toString(String [] args) {
        String res = "";
        for (int i = 0; i<args.length; ++i) {
            res +=args[i]+ " ";
        }
        return res;
    }


    private static void setup(Tree tree) {
        for (int i = 0; i < Data.templates.length; i++) {
            tree.insert(Data.templates[i],Data.commands[i]);
        }
    }
}
