package pt.isel.ls;


import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.CommandManager;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.manager.Request;
import pt.isel.ls.manager.Result;
import pt.isel.ls.representation.json.JSONObject;
import pt.isel.ls.representation.json.JSONWriter;
import pt.isel.ls.manager.Tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    //java -cp "vendor/main/*;build/classes/main" pt.isel.ls.App GET /checklists
    public static void main(String[] args) throws IOException {

        Tree tree = new Tree();
        setup(tree);

        Request rq = new Request(args);
        Command command = tree.search(rq);
        CommandManager cmdManager = new CommandManager(rq);


        try {

            Result result = command.execute(cmdManager);
            result.consumer(cmdManager);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setup(Tree tree) {
        for (int i = 0; i < Data.templates.length; i++) {
            tree.insert(Data.templates[i],Data.commands[i]);
        }
    }
}
