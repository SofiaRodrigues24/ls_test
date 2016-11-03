package pt.isel.ls.commands;

import pt.isel.ls.manager.Result;

import java.sql.SQLException;


public interface Command {
    Result execute(CommandManager manager) throws Exception;
}
