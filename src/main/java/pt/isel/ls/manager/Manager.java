package pt.isel.ls.manager;

import javafx.util.Pair;
import pt.isel.ls.commands.Command;

import java.util.List;
import java.util.regex.Pattern;

public class Manager {

    private List<Command> commands;

    public Manager(List<Command> commands) {
        this.commands = commands;
    }

    public void add(Command command) {
        commands.add(command);
    }

    public Command getCommand(Request rq) throws Exception {
        Pattern p = null;

        for (Command c: commands){
            p = Pattern.compile(c.getRegularExpression());
            if(rq.getArgs().matches(p.pattern()))
                return c;
        }
        throw new Exception("This command doesn't exist");
    }
}
