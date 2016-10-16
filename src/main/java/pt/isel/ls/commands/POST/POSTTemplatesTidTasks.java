package pt.isel.ls.commands.POST;


import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;


public class POSTTemplatesTidTasks implements Command {


    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int lid = 0;

        con.setAutoCommit(false);
        final String query = "insert into task (name, task_description) values (?, ?)";
        PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, map.get("name"));
        statement.setString(2, map.get("description"));
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if(generatedKeys.next())
            lid = generatedKeys.getInt(1);

        statement.close();

        String str = "insert into task_temp (lid, tid) values (?, ?)";

        PreparedStatement ps = con.prepareStatement(str);

        ps.setInt(1, lid);
        ps.setInt(2, Integer.parseInt(map.get("{tid}")));
        ps.executeUpdate();
        ps.close();

        con.commit();
        con.setAutoCommit(true);

        return new Result<>(lid);
    }

}
