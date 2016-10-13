package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;
import pt.isel.ls.domain.CheckList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GETChecklists implements Command {

    private List<CheckList> checklists;

    @Override
    public void execute(HashMap<String, String> map) throws SQLException {
		//TODO: receber um printstream para imprimir não é necessário usar o domain
		
        checklists = new ArrayList<>();

        try (Connection con = getConnection().getConnection()) {
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
        }
    }

    @Override
    public String getRegularExpression() {
        return "GET /checklists$";
    }

    @Override
    public void print() {
        System.out.println(checklists.toString());

    }
}
