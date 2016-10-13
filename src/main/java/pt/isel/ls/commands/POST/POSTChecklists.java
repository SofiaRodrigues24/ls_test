package pt.isel.ls.commands.POST;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.CheckList;

import java.sql.*;
import java.util.HashMap;


public class POSTChecklists  implements Command {

    private CheckList checkList;

    @Override
    public void execute(HashMap<String, String> map) throws SQLException {
        try(Connection con = getConnection().getConnection()) {
            final String query = "insert into checklist (name, check_description, duedate, completed) values (?, ?, ?,?)";
            PreparedStatement statement = con.prepareStatement(query);


            checkList = new CheckList(map.get("name"), map.get("description"), Date.valueOf(map.get("duedate")), false);

            statement.setString(1, checkList.getName());
            statement.setString(2, checkList.getDescription());
            statement.setDate(3, checkList.getDuedate());
            statement.setBoolean(4,false);


//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            checkList = new CheckList(generatedKeys.getInt(1));
            statement.executeUpdate();
            con.commit();
        }


    }

    @Override
    public String getRegularExpression() {
        return "POST /checklists/name=[\\w+]{1,30}&description=[\\w+]{1,80}" +
                "($|&duedate=\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d)";
    }

    @Override
    public void print() {
        checkList.toString();
    }
}
