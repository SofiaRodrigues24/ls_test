package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.domain.Tag;
import pt.isel.ls.domain.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GETTags extends CommandWithConnection {
    @Override
    protected Result<Collections<Tag>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        List<Tag> tags = new ArrayList<>();
        String query = "select * from tag";

        try (PreparedStatement statement = con.prepareStatement(query)){
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                tags.add(new Tag().populate(rs));
            }

        }

        Collections<Tag> col = new Collections<>("tag", tags);
        return new Result<>(col);
    }
}
