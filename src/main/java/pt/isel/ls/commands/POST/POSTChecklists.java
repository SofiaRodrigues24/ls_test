package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.exception.FailedExecuteException;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;


public class POSTChecklists  extends CommandWithConnection {

    @Override
    protected Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int id = 0;
        String query = "insert into checklist (check_name, check_description, check_duedate, completed) values (?, ?, ?, ?)";

        try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, map.get("name"));
            statement.setString(2, map.get("description"));
            statement.setBoolean(4, false);

            if(map.containsKey("duedate"))
                statement.setDate(3, Date.valueOf(map.get("duedate")));
            else statement.setDate(3, null);

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getInt(1);

        }

        return new Result<>(id);
    }

    @Override
    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("name") && parameters.containsKey("description");
    }
}
