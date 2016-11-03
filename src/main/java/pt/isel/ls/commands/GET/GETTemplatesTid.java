package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Template;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GETTemplatesTid extends CommandWithConnection {


    @Override
    protected Result<Template> execute(Connection con, HashMap<String, String> map) throws SQLException {
        Template temp = null;

        String query = "select * from template where tid = ?";

        String query1 = "select * from task inner join task_template " +
                "on (task_template.tid = ? and task.lid = task_template.lid)";

        String query2 = "select * from template inner join checklist " +
                "on (template.tid = ? and template.tid = checklist.tid)";

        con.setAutoCommit(false);

        try(PreparedStatement statement = con.prepareStatement(query);
            PreparedStatement statement1 = con.prepareStatement(query1);
            PreparedStatement statement2 = con.prepareStatement(query2)) {

            statement.setInt(1, Integer.parseInt(map.get("{tid}")));
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                temp = new Template().populate(rs);
            }

            statement1.setInt(1, temp.getTid());
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()) {
                temp.populateTasks(rs1);
            }

            statement2.setInt(1, temp.getTid());
            ResultSet rs2 = statement2.executeQuery();
            while (rs2.next()) {
               temp.populateChecklists(rs2);
            }

        }
        return new Result<>(temp);
    }

    @Override
    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("{tid}");
    }
}
