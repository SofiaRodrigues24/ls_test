package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTChecklistsCidTags extends CommandWithConnection {

    @Override
    protected Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int id = 0;
        String query = "insert into tags_checklists (cid, gid) values (?, ?)";

        try(PreparedStatement statement = con.prepareStatement(query)) {
            id = Integer.parseInt(map.get("{cid}"));
            statement.setInt(1, id);
            statement.setInt(2, Integer.parseInt(map.get("gid")));
            statement.executeUpdate();
        }
        return new Result<>(id);
    }

    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("gid") && parameters.containsKey("{cid}");
    }
}
