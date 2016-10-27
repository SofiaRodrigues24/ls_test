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

public class GETChecklistsClosed implements Command {


    @Override
    public Result<List<CheckList>> execute(Connection con, HashMap<String, String> map) {
        List<CheckList> checklists = new ArrayList<>();
        String query = "select * from checklist where completed = ?";

        try (PreparedStatement statement = con.prepareStatement(query)){

            statement.setBoolean(1, true);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                checklists.add(new CheckList().create(rs));
            }

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - rollback");
            }
            System.out.println("error - connection");
        }

        return new Result<>(checklists);
    }

}
