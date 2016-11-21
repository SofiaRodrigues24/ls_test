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
public class DELETETags extends CommandWithConnection {
    protected Result<Integer> execute(Connection con, HashMap<String, String>map)throws SQLException{
        String query = "delete * from tag";
        try(PreparedStatement statement = con.prepareStatement(query)) {
            statement.executeUpdate();

        }
        //return  new Result<>(Integer.parseInt());
        return null;
    }
    //nao é necessário parametros como fazemos ??
    @Override
    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("{gid}");
    }
}


