package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.Task;
import pt.isel.ls.domain.Template;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class POSTTemplatesTidCreate implements Command {


    @Override
    public Result<Integer> execute(Connection con, HashMap<String, String> map) {
        int id = 0;
        Template template = null;
        List<Task> tasks = new ArrayList<>();

        String query = "select * from template inner join task_template " +
                "on (template.tid = ? and template.tid = task_template.tid) inner join task " +
                "on (task_template.lid = task.lid)";
        String query1 = "insert into checklist(check_name, check_description, check_duedate, tid) values (?, ?, ?, ?)";


        try (PreparedStatement statement = con.prepareStatement(query);
            PreparedStatement statement1 = con.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){

            statement.setInt(1, Integer.parseInt(map.get("{tid}")));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if(template == null) {
                    template = new Template(
                            rs.getInt("tid"), rs.getString("temp_name"), rs.getString("temp_description")
                    );
                }

                tasks.add(new Task(rs.getInt("lid"), rs.getString("task_name"), rs.getString("task_description")));
            }

            statement1.setString(1, map.containsKey("name")?map.get("name"):template.getName());
            statement1.setString(2, map.containsKey("description")?map.get("description"):template.getDescription());
            statement1.setDate(3, map.containsKey("duedate")?Date.valueOf(map.get("duedate")):null);
            statement1.setInt(4, template.getTid());

            statement1.executeUpdate();

            ResultSet generatedKeys = statement1.getGeneratedKeys();
            while (generatedKeys.next())
                id = generatedKeys.getInt(1);


            for (Task t: tasks) {
                addTasks(con, id, t);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Result<>(id);
    }

    private void addTasks(Connection con, int cid, Task t) throws SQLException {
        String query1 = "insert into task_check (task_duedate, isClosed, lid, cid) values (?, ?, ?, ?)";

        con.setAutoCommit(false);
        try (PreparedStatement statement1 = con.prepareStatement(query1)){

            statement1.setDate(1, null);
            statement1.setBoolean(2, false);
            statement1.setInt(3, t.getLid());
            statement1.setInt(4, cid);
            statement1.executeUpdate();

            con.commit();
            con.setAutoCommit(true);
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - roolback");
            }
            System.out.println("error - connection");
        }
    }


}
