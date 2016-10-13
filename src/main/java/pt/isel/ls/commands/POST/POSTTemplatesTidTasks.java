package pt.isel.ls.commands.POST;


import pt.isel.ls.commands.Command;

import java.util.HashMap;


public class POSTTemplatesTidTasks implements Command {


    @Override
    public void execute(HashMap<String, String> map) {

    }

    @Override
    public String getRegularExpression() {
        return "POST /templates/\\d{1,}/tasks/name=[\\w+]{1,}&description=[\\w+]{1,}";
    }

    @Override
    public void print() {

    }
}
