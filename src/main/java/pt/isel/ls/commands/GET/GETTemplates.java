package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;

import java.util.HashMap;

public class GETTemplates implements Command {


    @Override
    public void execute(HashMap<String, String> map) {

    }

    @Override
    public String getRegularExpression() {
        return "GET /templates$";
    }

    @Override
    public void print() {

    }
}
