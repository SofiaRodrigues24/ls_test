package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTTemplates extends CommandWithConnection {

    @Override
    protected Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int id = 0;
        String query = "insert into template (temp_name, temp_description) values (?, ?)";

        try(PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, map.get("name"));
            statement.setString(2, map.get("description"));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getInt(1);

        }
        return new Result<>(id);

    }

    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("name") && parameters.containsKey("description");
    }
}
