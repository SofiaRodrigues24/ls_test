package pt.isel.ls.commands.POST;


import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;


public class POSTTemplatesTidTasks implements Command {


    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int lid = 0;

        String query = "insert into task (task_name, task_description) values (?, ?)";
        String query1 = "insert into task_template (lid, tid) values (?, ?)";

        con.setAutoCommit(false);
        try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statement1 = con.prepareStatement(query1);){

            statement.setString(1, map.get("name"));
            statement.setString(2, map.get("description"));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                lid = generatedKeys.getInt(1);

            statement1.setInt(1, lid);
            statement1.setInt(2, Integer.parseInt(map.get("{tid}")));
            statement1.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - rollback");
            }
            System.out.println("error - connection");
            e.printStackTrace();
        }
        return new Result<>(lid);
    }

}
