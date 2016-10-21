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
    public Result<List<Template>> execute(Connection con, HashMap<String, String> map) {
        List<Template> templates = null;
        String query = "select * from template";

        try ( PreparedStatement statement = con.prepareStatement(query)){

            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                if(templates == null)
                    templates = new ArrayList<>();

                templates.add(new Template(
                        rs.getInt("tid"), rs.getString("temp_name"),
                        rs.getString("temp_description"))
                );
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

        return new Result<>(templates);
    }

}
