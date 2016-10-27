package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Tag;
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

        String query = "select * from checklist where checklist.cid = ?";

        String query1 =  "select * from task inner join task_check " +
                "on (task_check.cid = ? and task.lid = task_check.lid)";

        String query2 = "select * from tag inner join tags_checklists" +
                "on (tag.gid = tags_checklists.gid and tagas_checklists.cid = ?)";

        con.setAutoCommit(false);
        try(PreparedStatement statement = con.prepareStatement(query);
            PreparedStatement statement1 = con.prepareStatement(query1);
            PreparedStatement statement2 = con.prepareStatement(query2)) {

            statement.setInt(1, Integer.parseInt(map.get("{cid}")));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                    checkList = new CheckList().create(rs);
            }

            statement1.setInt(1, checkList.getCid());
            ResultSet rs1 = statement1.executeQuery();
            while (rs1.next()) {
                checkList.addTask(rs1);
            }

            statement2.setInt(1, Integer.parseInt(map.get("{cid}")));
            ResultSet rs2 = statement2.executeQuery();

            while (rs2.next()) {
                checkList.addTag(rs2);
            }
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
