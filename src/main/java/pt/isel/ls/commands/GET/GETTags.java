package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.Tag;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GETTags implements Command {
    @Override
    public Result<List<Tag>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        List<Tag> tags = new ArrayList<>();

        String query = "select * from tag";

        try (PreparedStatement statement = con.prepareStatement(query)){
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Tag tag = new Tag(
                        rs.getInt("gid"), rs.getString("tag_name"),
                        rs.getInt("color")
                );

                tags.add(tag);
            }

            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - rollback");
            }
            System.out.println("error - connection");
        }

        return new Result<>(tags);
    }
}
