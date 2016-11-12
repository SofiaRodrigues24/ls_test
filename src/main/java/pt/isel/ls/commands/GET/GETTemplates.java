package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.domain.Template;
import pt.isel.ls.domain.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GETTemplates extends CommandWithConnection {


    @Override
    public Result<Collections<Template>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        List<Template> templates = new ArrayList<>();
        String query = "select * from template";

        try ( PreparedStatement statement = con.prepareStatement(query)){

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                templates.add(new Template().populate(rs));
            }

        }

        Collections<Template> col = new Collections<>("template", templates);

        return new Result<>(col);
    }

}
