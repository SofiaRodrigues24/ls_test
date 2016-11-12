package pt.isel.ls.commands;

import pt.isel.ls.exception.ParametersException;
import pt.isel.ls.manager.CommandManager;
import pt.isel.ls.domain.Result;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class CommandWithConnection implements Command {

    @Override
    public Result execute(CommandManager manager) throws ParametersException, IOException, SQLException {
        if(!hasParameters(manager.getParameters()))
            throw new ParametersException("Invalid parameters");

        Connection con = null;
        Result execute = null;
        try {
            con = manager.getConnection();
            execute = execute(con, manager.getParameters());
            con.commit();
            con.close();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }

        return execute;
    }

    protected abstract Result execute(Connection con, HashMap<String, String> map) throws SQLException;

    protected boolean hasParameters(HashMap<String, String> parameters) {
        return true;
    }

}
