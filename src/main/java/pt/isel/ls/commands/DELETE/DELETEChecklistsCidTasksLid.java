package pt.isel.ls.commands.DELETE;

import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Sonia on 20-11-2016.
 */
public class DELETEChecklistsCidTasksLid extends CommandWithConnection {
    @Override
    protected Result execute(Connection con, HashMap<String, String> map) throws SQLException {
        String query = "delete from task_check where cid = ? and lid = ?";

        try(PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(map.get("{cid}")));
            statement.setInt(2, Integer.parseInt(map.get("{lid}")));
            statement.executeUpdate();

        }

        return new Result<>(Integer.parseInt(map.get("{lid}")));

    }
    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("{cid}") && parameters.containsKey("{lid}");
    }
}
