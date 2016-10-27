package pt.isel.ls.commands.DELETE;


import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class DELETEChecklistsCidTagsGid implements Command{


    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        String query = "delete from tags_checklists where cid = ? and gid = ?";

        try(PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, Integer.parseInt(map.get("{cid}")));
            statement.setInt(2, Integer.parseInt(map.get("{gid}")));
            statement.executeUpdate();

            con.commit();
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - rollback");
            }
            System.out.println("error - connection");
        }

        return new Result<>(Integer.parseInt(map.get("{gid}")));
    }
}
