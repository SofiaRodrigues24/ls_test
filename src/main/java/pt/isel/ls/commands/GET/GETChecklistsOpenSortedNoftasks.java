package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GETChecklistsOpenSortedNoftasks implements Command {

    @Override
    public Result<List<CheckList>> execute(Connection con, HashMap<String, String> map) throws SQLException{
        List<CheckList> checkLists = new ArrayList<>();
        String query = "select * from checklist inner join (" +
                        "select task_check.cid, " +
                            "count(lid) as c from task_check inner join checklist " +
                                "on (task_check.cid = checklist.cid and task_check.isClosed = ?) " +
                            "group by checklist.cid, task_check.cid) " +
                        "as aux on (checklist.cid = aux.cid and completed = ?) " +
                        "order by aux.c desc";

        con.setAutoCommit(false);
        try(PreparedStatement statement = con.prepareStatement(query)) {
            statement.setBoolean(1, false);
            statement.setBoolean(2, false);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CheckList c = new CheckList(
                        rs.getInt("cid"),
                        rs.getString("check_name"),
                        rs.getString("check_description"));
                checkLists.add(c);
            }

            con.commit();
            con.setAutoCommit(true);

        } catch( SQLException e){
            con.rollback();
        }
        return new Result<>(checkLists);
    }
}
