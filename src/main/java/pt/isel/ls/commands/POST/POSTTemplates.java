package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTTemplates implements Command {

    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {

        int id = 0;
        final String query = "insert into template (name, temp_description) values (?, ?)";
        PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, map.get("name"));
        statement.setString(2, map.get("description"));
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if(generatedKeys.next())
            id = generatedKeys.getInt(1);

        con.commit();

        return new Result<>(id);

    }

}
