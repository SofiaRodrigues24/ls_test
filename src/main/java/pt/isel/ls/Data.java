package pt.isel.ls;

import pt.isel.ls.commands.Command;
import pt.isel.ls.commands.GET.*;
import pt.isel.ls.commands.POST.*;


public class Data {

    public static Command[] commands = {new GETChecklists(), new GETChecklistsCid(), new GETChecklistsClosed(),
            new GETChecklistsOpenSortedDuedate(), new GETChecklistsOpenSortedNoftasks(),
            new GETTemplates(), new GETTemplatesTid(),
            new POSTChecklists(), new POSTChecklistsCidTasks(), new POSTChecklistsCidTasksLid(),
            new POSTTemplates(), new POSTTemplatesTidCreate(), new POSTTemplatesTidTasks()
    };

}
