package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.Task;
import pt.isel.ls.domain.Template;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class POSTTemplatesTidCreate extends CommandWithConnection {


    @Override
    protected Result<Integer> execute(Connection con, HashMap<String, String> map) throws SQLException {
        int id = 0;
        Template template = null;

        String query = "select * from template where tid = ?";
        String query1 = "insert into checklist(check_name, check_description, check_duedate, tid) values (?, ?, ?, ?)";
        String query2 = "select * from task_template where tid = ?";
        String query3 = "insert into task_check (task_duedate, isClosed, lid, cid) values (?, ?, ?, ?)";

        try (PreparedStatement statement = con.prepareStatement(query);
             PreparedStatement statement1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statement2 = con.prepareStatement(query2);
             PreparedStatement statement3 = con.prepareStatement(query3)
        ){
            statement.setInt(1, Integer.parseInt(map.get("{tid}")));
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                template = new Template().populate(rs);

            statement1.setString(1, map.containsKey("name")?map.get("name"):template.getName());
            statement1.setString(2, map.containsKey("description")?map.get("description"):template.getDescription());
            statement1.setDate(3, map.containsKey("duedate")?Date.valueOf(map.get("duedate")):null);
            statement1.setInt(4, template.getTid());
            statement1.executeUpdate();

            ResultSet generatedKeys = statement1.getGeneratedKeys();
            while (generatedKeys.next())
                id = generatedKeys.getInt(1);

            statement2.setInt(1, template.getTid());
            ResultSet rs2 = statement2.executeQuery();

            while (rs2.next()) {
                statement3.setDate(1, null);
                statement3.setBoolean(2, false);
                statement3.setInt(3, rs2.getInt("lid"));
                statement3.setInt(4, id);
                statement3.executeUpdate();
            }
        }
        return new Result<>(id);
    }

    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("{tid}");
    }

}
