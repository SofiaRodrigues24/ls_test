package pt.isel.ls.commands;

import pt.isel.ls.manager.Result;

/**
 * Created by Eliane on 03/11/2016.
 */
public class EXIT implements Command{
    @Override
    public Result execute(CommandManager manager) throws Exception {
        System.exit(1);
        return null;
    }
}
