package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GETChecklistsOpenSortedNoftasks extends CommandWithConnection {

    @Override
    protected Result<Collections<CheckList>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        List<CheckList> checkLists = new ArrayList<>();
        String query = "select * from checklist inner join (" +
                        "select task_check.cid, " +
                            "count(lid) as c from task_check inner join checklist " +
                                "on (task_check.cid = checklist.cid and task_check.isClosed = ?) " +
                            "group by checklist.cid, task_check.cid) " +
                        "as aux on (checklist.cid = aux.cid and completed = ?) " +
                        "order by aux.c desc";

        try(PreparedStatement statement = con.prepareStatement(query)) {
            statement.setBoolean(1, false);
            statement.setBoolean(2, false);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                checkLists.add(new CheckList().populate(rs));
            }
        }
        Collections<CheckList> col = new Collections<>("checklist", checkLists);
        return new Result<>(col);
    }
}
