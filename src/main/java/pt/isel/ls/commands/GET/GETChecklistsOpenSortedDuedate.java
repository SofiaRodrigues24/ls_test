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

public class GETChecklistsOpenSortedDuedate implements Command{


    @Override
    public Result<ArrayList<CheckList>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        ArrayList<CheckList> checkLists = new ArrayList<>();
        String sql = "select * from checklist where completed = ? order by check_duedate asc";

        try(PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, false);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CheckList c = new CheckList(rs.getInt("cid"),rs.getString("check_name"), rs.getString("check_description"),
                        rs.getDate("check_duedate"), rs.getBoolean("completed"));
                checkLists.add(c);
            }

            con.commit();
            con.setAutoCommit(true);
        }catch (SQLException e){
            con.rollback();
        }
        return new Result<>(checkLists);
    }

}
