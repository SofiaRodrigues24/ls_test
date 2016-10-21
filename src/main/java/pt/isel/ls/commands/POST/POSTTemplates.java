package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTTemplates implements Command {

    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map)  {
        int id = 0;
        String query = "insert into template (temp_name, temp_description) values (?, ?)";

        try(PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, map.get("name"));
            statement.setString(2, map.get("description"));
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getInt(1);

            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - rollback");
            }
            System.out.println("error - connection");
        }
        return new Result<>(id);

    }

}
