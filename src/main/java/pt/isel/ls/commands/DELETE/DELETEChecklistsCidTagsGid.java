package pt.isel.ls.commands.DELETE;


import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class DELETEChecklistsCidTagsGid extends CommandWithConnection{


    @Override
    protected Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        String query = "delete from tags_checklists where cid = ? and gid = ?";

        try(PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(map.get("{cid}")));
            statement.setInt(2, Integer.parseInt(map.get("{gid}")));
            statement.executeUpdate();

        }

        return new Result<>(Integer.parseInt(map.get("{gid}")));
    }

    @Override
    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("{cid}") && parameters.containsKey("{gid}");
    }
}
