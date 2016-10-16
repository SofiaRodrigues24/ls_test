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

public class GETChecklists implements Command {

    @Override
    public Result<List<CheckList>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        List<CheckList> checklists = new ArrayList<>();

        con.setAutoCommit(false);
        final String query = "select * from checklist";

        PreparedStatement statement = con.prepareStatement(query);

        ResultSet rs = statement.executeQuery();

        while(rs.next()) {
            CheckList c = new CheckList(
                    rs.getInt("cid"), rs.getString("name"),
                    rs.getString("check_description"), rs.getDate("duedate"));
            checklists.add(c);

        }

        con.commit();
        con.setAutoCommit(true);

        return new Result<>(checklists);
    }

}
