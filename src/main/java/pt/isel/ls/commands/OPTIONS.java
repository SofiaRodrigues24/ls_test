package pt.isel.ls.commands;

import pt.isel.ls.manager.CommandManager;
import pt.isel.ls.domain.Result;

public class OPTIONS implements Command {
    @Override
    public Result<String> execute(CommandManager manager) throws Exception {
        String options = "GET \n" +
                "\t /checklists\n" +
                "\t /checklists/{cid}\n" +
                "\t /checklists/closed\n" +
                "\t /checklists/open/sorted/duedate\n" +
                "\t /checklists/open/sorted/noftasks\n" +
                "\t /tags\n" +
                "\t /templates\n" +
                "\t /templates{tid}\n" +
                "POST \n" +
                "\t /checklists\n" +
                "\t /checklists/{cid}/tags\n" +
                "\t /checklists/{cid}/tasks\n" +
                "\t /checklists/{cid}/tasks/{lid}\n" +
                "\t /tags\n" +
                "\t /templates\n" +
                "\t /templates/{tid}/create\n" +
                "\t /templates/{tid}/tasks\n" +
                "DELETE \n" +
                "\t /checklists/{cid}/tags/gid\n" +
                "\t /tags/gid\n";
        return new Result<>(options);
    }
}
