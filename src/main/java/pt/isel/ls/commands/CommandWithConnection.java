package pt.isel.ls.commands;

import pt.isel.ls.exception.ParametersException;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class CommandWithConnection implements Command {

    @Override
    public Result execute(CommandManager manager) throws Exception {
        if(!hasParameters(manager.getParameters()))
            throw new ParametersException("Invalid parameters");

        Connection con = manager.getConnection();
        Result execute = execute(con, manager.getParameters());
        con.commit();
        con.close();
        return manager.getResult(execute);
    }

    protected abstract Result execute(Connection con, HashMap<String, String> map) throws SQLException;

    protected boolean hasParameters(HashMap<String, String> parameters) {
        return true;
    }

}
