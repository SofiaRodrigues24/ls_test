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

public class GETChecklistsClosed extends CommandWithConnection {


    @Override
    protected Result<Collections<CheckList>> execute(Connection con, HashMap<String, String> map) throws SQLException {
        List<CheckList> checklists = new ArrayList<>();
        String query = "select * from checklist where completed = ?";

        try(PreparedStatement statement = con.prepareStatement(query)) {

            statement.setBoolean(1, true);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                checklists.add(new CheckList().populate(rs));
            }
        }

        Collections<CheckList> col = new Collections<>("checklist", checklists);
        return new Result<>(col);
    }

}
