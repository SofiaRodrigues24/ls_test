package pt.isel.ls.commands;

import pt.isel.ls.manager.Result;

public class OPTIONS implements Command {
    @Override
    public Result<String> execute(CommandManager manager) throws Exception {
        String options = "";
        return new Result<>(options);
    }
}
