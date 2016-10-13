package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;

import java.util.HashMap;

public class GETChecklistsClosed implements Command {


    @Override
    public void execute(HashMap<String, String> map) {

    }

    @Override
    public String getRegularExpression() {
        return "GET /checklists/closed$";
    }

    @Override
    public void print() {

    }
}
