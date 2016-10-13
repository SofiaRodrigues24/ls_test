package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;

import java.util.HashMap;

/**
 * Created by Eliane on 08/10/2016.
 */
public class POSTChecklistsCidTasksLid implements Command {


    @Override
    public void execute(HashMap<String, String> map) {

    }

    @Override
    public String getRegularExpression() {
        return "POST /checklists/[0-9]{1,}/tasks/[0-9]{1,}/isClosed=(true|false)";
    }

    @Override
    public void print() {

    }
}
