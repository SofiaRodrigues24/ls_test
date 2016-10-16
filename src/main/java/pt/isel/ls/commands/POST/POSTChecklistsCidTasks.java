package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTChecklistsCidTasks implements Command {


    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {

        int id = 0;

        con.setAutoCommit(false);
        final String query = "insert into task (name, task_description) values (?, ?)";
        PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, map.get("name"));
        statement.setString(2, map.get("description"));
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if(generatedKeys.next())
            id = generatedKeys.getInt(1);

        statement.close();

        String str = "insert into task_check (duedate, isClosed, lid, cid) values (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(str);

        if(map.containsKey("duedate")) {
            ps.setDate(1, Date.valueOf(map.get("duedate")));
        }
        else ps.setDate(1, null);

        ps.setBoolean(2, false);
        ps.setInt(3, id);
        ps.setInt(4, Integer.parseInt(map.get("{cid}")));
        ps.executeUpdate();
        ps.close();

        con.commit();
        con.setAutoCommit(true);

        return new Result<>(id);

    }

}
