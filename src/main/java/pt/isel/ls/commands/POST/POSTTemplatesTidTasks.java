package pt.isel.ls.commands.POST;


import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Result;

import java.sql.*;
import java.util.HashMap;


public class POSTTemplatesTidTasks extends CommandWithConnection {

    @Override
    protected Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int lid = 0;

        String query = "insert into task (task_name, task_description) values (?, ?)";
        String query1 = "insert into task_template (lid, tid) values (?, ?)";
        con.setAutoCommit(false);
        try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statement1 = con.prepareStatement(query1);){

            statement.setString(1, map.get("name"));
            statement.setString(2, map.get("description"));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                lid = generatedKeys.getInt(1);

            statement1.setInt(1, lid);
            statement1.setInt(2, Integer.parseInt(map.get("{tid}")));
            statement1.executeUpdate();
        }
        return new Result<>(lid);
    }

    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("name") && parameters.containsKey("description")
                && parameters.containsKey("{tid}");
    }
}
