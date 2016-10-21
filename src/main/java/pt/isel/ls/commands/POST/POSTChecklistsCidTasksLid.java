package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;
import pt.isel.ls.manager.Result;

import java.sql.*;
import java.util.HashMap;

public class POSTChecklistsCidTasksLid implements Command {


    @Override
    public Result execute(Connection con, HashMap<String, String> map) {
        String query = "update task_check set isClosed = ? where cid = ? and lid = ?";
        try (PreparedStatement statement = con.prepareStatement(query)){

            statement.setString(1, map.get("isClosed"));
            statement.setInt(2,Integer.parseInt(map.get("{cid}")));
            statement.setInt(3,Integer.parseInt(map.get("{lid}")));
            statement.executeUpdate();

            con.commit();

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("error - rollback");
            }
            System.out.println("error - connection");
        }


        return new Result("ok");
    }

}
