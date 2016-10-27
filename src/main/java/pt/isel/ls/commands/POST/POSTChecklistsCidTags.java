package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTChecklistsCidTags implements Command {

    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int id = 0;
        String query = "insert into tags_checklists (cid, gid) values (?, ?)";

        try(PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet generatedKeys = null;
            statement.setInt(1, Integer.parseInt(map.get("{cid}")));
            statement.setInt(2, Integer.parseInt(map.get("gid")));
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
