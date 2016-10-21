package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTChecklistsCidTasks implements Command {


    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
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

            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - roolback");
            }
            System.out.println("error - connection");
        }
        return new Result<>(id);

    }

}
