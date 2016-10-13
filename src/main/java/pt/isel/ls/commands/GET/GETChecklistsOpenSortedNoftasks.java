package pt.isel.ls.commands.GET;

import pt.isel.ls.commands.Command;

import java.util.HashMap;

/**
 * Created by Eliane on 08/10/2016.
 */
public class GETChecklistsOpenSortedNoftasks implements Command {


    @Override
    public void execute(HashMap<String, String> map) {

    }

    @Override
    public String getRegularExpression() {
        return "GET /checklists/open/sorted/noftasks$";
    }

    @Override
    public void print() {

    }
}
