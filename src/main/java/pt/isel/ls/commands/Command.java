package pt.isel.ls.commands;

import pt.isel.ls.manager.CommandManager;
import pt.isel.ls.domain.Result;


public interface Command {
    Result execute(CommandManager manager) throws Exception;
}
