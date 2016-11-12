package pt.isel.ls.commands;

import pt.isel.ls.manager.CommandManager;
import pt.isel.ls.domain.Result;

public class EXIT implements Command{
    @Override
    public Result execute(CommandManager manager) throws Exception {
        System.exit(1);
        return null;
    }
}
