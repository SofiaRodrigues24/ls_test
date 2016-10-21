package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;


public class POSTChecklists  implements Command {

    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) {
        int id = 0;
        String query = "insert into checklist (check_name, check_description, check_duedate) values (?, ?, ?)";

        try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            ResultSet generatedKeys = null;

            statement.setString(1, map.get("name"));
            statement.setString(2, map.get("description"));

            if(map.containsKey("duedate"))
                statement.setDate(3, Date.valueOf(map.get("duedate")));
            else statement.setDate(3, null);

            statement.executeUpdate();

            generatedKeys = statement.getGeneratedKeys();
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
