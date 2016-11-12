package pt.isel.ls;

import pt.isel.ls.commands.*;
import pt.isel.ls.commands.DELETE.DELETEChecklistsCidTagsGid;
import pt.isel.ls.commands.DELETE.DELETETagsGid;
import pt.isel.ls.commands.GET.*;
import pt.isel.ls.commands.POST.*;
import pt.isel.ls.manager.Tree;

public class TreeUtilsTest {

    public static String [] templates = {
            "GET/checklists", "GET/checklists/{cid}", "GET/checklists/closed",
            "GET/checklists/open/sorted/duedate", "GET/checklists/open/sorted/noftasks",
            "GET/templates", "GET/templates/{tid}",
            "POST/checklists", "POST/checklists/{cid}/tasks", "POST/checklists/{cid}/tasks/{lid}",
            "POST/templates", "POST/templates/{tid}/create", "POST/templates/{tid}/tasks",
            "POST/tags", "GET/tags", "DELETE/tags/{gid}", "POST/checklists/{cid}/tags",
            "DELETE/checklists/{cid}/tags/{gid}", "EXIT/", "OPTIONS/"
    };

    public static Command[] commands = {
            new GETChecklists(), new GETChecklistsCid(), new GETChecklistsClosed(),
            new GETChecklistsOpenSortedDuedate(), new GETChecklistsOpenSortedNoftasks(),
            new GETTemplates(), new GETTemplatesTid(),
            new POSTChecklists(), new POSTChecklistsCidTasks(), new POSTChecklistsCidTasksLid(),
            new POSTTemplates(), new POSTTemplatesTidCreate(), new POSTTemplatesTidTasks(),
            new POSTTags(), new GETTags(), new DELETETagsGid(),  new POSTChecklistsCidTags(),
            new DELETEChecklistsCidTagsGid(), new EXIT(), new OPTIONS()
    };

    public static void initTree(Tree root) {
        for (int i = 0; i < templates.length; i++) {
            root.insert(templates[i], commands[i]);
        }
    }
}
