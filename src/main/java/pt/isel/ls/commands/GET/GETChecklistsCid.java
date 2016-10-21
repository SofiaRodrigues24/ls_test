package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Task;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GETChecklistsCid implements Command {

    @Override
    public Result<CheckList> execute(Connection con, HashMap<String, String> map) throws SQLException {
        CheckList checkList = null;
        List<Task> tasks = null;

        String query = "select * from checklist where checklist.cid = ?";

        String query1 =  "select * from task inner join task_check " +
                "on (task_check.cid = ? and task.lid = task_check.lid)";

        con.setAutoCommit(false);
        try(PreparedStatement statement = con.prepareStatement(query);
            PreparedStatement statement1 = con.prepareStatement(query1)) {

            statement.setInt(1, Integer.parseInt(map.get("{cid}")));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                    checkList = new CheckList(
                            rs.getInt("cid"), rs.getString("check_name"),
                            rs.getString("check_description"), rs.getDate("check_duedate")
                    );
            }

            statement1.setInt(1, checkList.getCid());
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()) {
                if(tasks == null)
                    tasks = new ArrayList<>();

                tasks.add(
                        new Task(
                                rs1.getInt("lid"), rs1.getString("task_name"),
                                rs1.getString("task_description"), rs1.getBoolean("isClosed"),
                                rs1.getDate("task_duedate")
                        )
                );

            }

            checkList.setTasks(tasks);
            con.commit();
            con.setAutoCommit(true);

        }catch (SQLException e){
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - rollback");
            }
            System.out.println("error - connection");
            e.printStackTrace();
        }
        return new Result<>(checkList);
    }


}
