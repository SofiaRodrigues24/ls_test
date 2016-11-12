package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.CommandWithConnection;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.domain.Collections;
import pt.isel.ls.domain.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GETChecklistsOpenSortedDuedate extends CommandWithConnection{


    @Override
    protected Result<Collections<CheckList>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        ArrayList<CheckList> checkLists = new ArrayList<>();
        String sql = "select * from checklist where completed = ? order by check_duedate asc";

        try(PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, false);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                checkLists.add(new CheckList().populate(rs));
            }

        }
        Collections<CheckList> col = new Collections<>("checklist", checkLists);
        return new Result<>(col);
    }

}
