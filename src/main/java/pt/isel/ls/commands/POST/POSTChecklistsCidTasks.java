package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTChecklistsCidTasks extends CommandWithConnection {


    @Override
    protected Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int id = 0;
        String query = "insert into task (task_name, task_description) values (?, ?)";
        String query1 = "insert into task_check (task_duedate, isClosed, lid, cid) values (?, ?, ?, ?)";

        con.setAutoCommit(false);
        try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statement1 = con.prepareStatement(query1)){

            statement.setString(1, map.get("name"));
            statement.setString(2, map.get("description"));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getInt(1);

            if(map.containsKey("duedate"))
                statement1.setDate(1, Date.valueOf(map.get("duedate")));
            else statement1.setDate(1, null);

            statement1.setBoolean(2, false);
            statement1.setInt(3, id);
            statement1.setInt(4, Integer.parseInt(map.get("{cid}")));
            statement1.executeUpdate();

        }
        return new Result<>(id);

    }

    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("name") && parameters.containsKey("description")
                && parameters.containsKey("{cid}");
    }

}
