package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Template;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GETTemplates implements Command {


    @Override
    public Result<List<Template>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        List<Template> templates = new ArrayList<>();

        con.setAutoCommit(false);
        final String query = "select * from template";

        PreparedStatement statement = con.prepareStatement(query);

        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            Template t = new Template(
                    rs.getInt("tid"), rs.getString("name"),
                    rs.getString("temp_description"));
            templates.add(t);

        }

        con.commit();
        con.setAutoCommit(true);

        return new Result<>(templates);
    }

}
