package pt.isel.ls;


import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.CommandManager;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Request;
import pt.isel.ls.manager.Result;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.representation.json.JSONWriter;
import pt.isel.ls.manager.Tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    //java -Xlint:unchecked -cp "vendor/main/*;build/classes/main" pt.isel.ls.App GET /checklists
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
