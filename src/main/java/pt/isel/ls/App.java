package pt.isel.ls;

import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.*;

import java.util.ArrayList;


public class App {

    public static void main(String[] args) throws Exception {
        Manager manager = new Manager(new ArrayList<Command>());

        setup(manager);
        Request rq = new Request(args);

        Command c = manager.getCommand(rq);
        c.execute(rq.getParameters());
        c.print();
    }

    private static void setup(Manager manager) {
        for (int i = 0; i < Data.commands.length; i++) {
            manager.add(Data.commands[i]);
        }
    }
}
