package pt.isel.ls.commands.POST;

import pt.isel.ls.commands.Command;

import java.util.HashMap;

/**
 * Created by Eliane on 08/10/2016.
 */
public class POSTTemplates implements Command {


    @Override
    public void execute(HashMap<String, String> map) {

    }

    @Override
    public String getRegularExpression() {
        return "POST /templates/name=[\\w+]{1,}&description=[\\w+]{1,}";
    }

    @Override
    public void print() {

    }
}
