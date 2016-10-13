package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;

import java.util.HashMap;

public class POSTTemplatesTidCreate implements Command {


    @Override
    public void execute(HashMap<String, String> map) {

    }

    @Override
    public String getRegularExpression() {
        return "POST /templates/[0-9]{1,}/create(/" +
                "(name=[\\w+]{1,}|" +
                "description=[\\w+]{1,}|" +
                "dueDate=\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d|" +
                "name=[\\w+]{1,}&description=[\\w+]{1,}|" +
                "name=[\\w+]{1,}&dueDate=\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d|" +
                "name=[\\w+]{1,}&description=[\\w+]{1,}&dueDate=\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d|" +
                "description=[\\w+]{1,}&dueDate=\\d\\d\\d\\d\\-\\d\\d\\-\\d\\d)|" +
                "$)";
    }

    @Override
    public void print() {

    }
}
