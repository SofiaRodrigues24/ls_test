package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GETChecklistsCid extends CommandWithConnection {

    @Override
    protected Result<CheckList> execute(Connection con, HashMap<String, String> map) throws SQLException {
        CheckList checkList = null;

        String query = "select * from checklist where checklist.cid = ?";

        String query1 =  "select * from task inner join task_check " +
                "on (task_check.cid = ? and task.lid = task_check.lid)";

        String query2 = "select * from tag inner join tags_checklists " +
                "on (tags_checklists.cid = ? and tag.gid = tags_checklists.gid)";
        con.setAutoCommit(false);
        try(PreparedStatement statement = con.prepareStatement(query);
            PreparedStatement statement1 = con.prepareStatement(query1);
            PreparedStatement statement2 = con.prepareStatement(query2)) {

            statement.setInt(1, Integer.parseInt(map.get("{cid}")));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                    checkList = new CheckList().populate(rs);
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

        }
        return new Result<>(checkList);
    }


    @Override
    protected boolean hasParameters(HashMap<String, String> parameters) {
        return parameters.containsKey("{cid}");
    }
}
