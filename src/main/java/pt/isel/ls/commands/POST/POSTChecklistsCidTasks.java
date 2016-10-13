package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;

import java.util.HashMap;

public class POSTChecklistsCidTasks implements Command {


    @Override
    public void execute(HashMap<String, String> map) {

    }

    @Override
    public String getRegularExpression() {
        return "POST /checklists/[0-9]{1,}/tasks/name=[\\w+]{1,}&description=[\\w+]{1,}" +
                "($|&dueDate=\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d)";
    }

    @Override
    public void print() {

    }
}
