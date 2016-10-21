package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Task;
import pt.isel.ls.domain.Template;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GETTemplatesTid implements Command {


    @Override
    public Result<Template> execute(Connection con, HashMap<String, String> map) throws SQLException {
        Template temp = null;
        List<Task> tasks = null;
        List<CheckList> checklists = null;

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
            while (rs.next()) {
                temp = new Template(rs.getInt("tid"), rs.getString("temp_name"), rs.getString("temp_description"));
            }

            statement1.setInt(1, temp.getTid());
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()) {
                if(tasks == null)
                    tasks = new ArrayList<>();

                tasks.add(
                        new Task(
                                rs1.getInt("lid"), rs1.getString("task_name"),
                                rs1.getString("task_description")
                        )
                );
            }
            temp.setTemplateTasks(tasks);

            statement2.setInt(1, temp.getTid());
            ResultSet rs2 = statement2.executeQuery();
            while (rs2.next()) {
                if(checklists == null)
                    checklists = new ArrayList<>();

                checklists.add(
                        new CheckList(
                                rs2.getInt("cid"), rs2.getString("check_name"),
                                rs2.getString("check_description")
                        )
                );
            }
            temp.setChecklists(checklists);

            con.setAutoCommit(true);
            con.commit();

       }catch (SQLException e){
            e.printStackTrace();
           con.rollback();
       }
        return new Result<>(temp);
    }

}
