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

/**
 * Created by Eliane on 08/10/2016.
 */
public class GETTemplatesTid implements Command {


    @Override
    public Result<Template> execute(Connection con, HashMap<String, String> map) throws SQLException {
       Template temp = null;

        con.setAutoCommit(false);
        String tid = map.get("tid");
        final String query = "select * from template inner join task_temp on " +
                "(template.tid = "+tid+" and template.tid = task_temp.tid) inner join checklist on" +
                "(template.tid = checklist.tid)";

        PreparedStatement statement = con.prepareStatement(query);

        ResultSet rs = statement.executeQuery();

        while(rs.next()) {

        }

        con.commit();
        con.setAutoCommit(true);
        return new Result<>(temp);
    }

}
