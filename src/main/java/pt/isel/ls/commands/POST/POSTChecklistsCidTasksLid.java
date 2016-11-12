package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTChecklistsCidTasksLid extends CommandWithConnection {


    @Override
    protected Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        String query = "update task_check set isClosed = ? where cid = ? and lid = ?";
        try (PreparedStatement statement = con.prepareStatement(query)){
            statement.setString(1, map.get("isClosed"));
            statement.setInt(2,Integer.parseInt(map.get("{cid}")));
            statement.setInt(3,Integer.parseInt(map.get("{lid}")));
            statement.executeUpdate();
        }


        Result<Integer> res = new Result<>(Integer.parseInt(map.get("{lid}")));

        //return new Result<Integer>(Integer.parseInt(map.get("{lid}")));
        return res;
    }

    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("isClosed");
    }
}
