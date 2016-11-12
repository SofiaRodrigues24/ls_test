package pt.isel.ls.tree;

import pt.isel.ls.commands.Command;

import java.util.HashMap;

public class TreeNode {
    private Command elem;
    private boolean isCommand;
    public HashMap<String, TreeNode> children;

    public Command getElem() {
        return elem;
    }

    public void setElem(Command elem) {
        this.elem = elem;
    }

    public boolean isCommand() {
        return isCommand;
    }

    public void setCommand(boolean command) {
        isCommand = command;
    }


}
