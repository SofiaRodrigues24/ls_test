package pt.isel.ls.manager;

import pt.isel.ls.commands.Command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tree {
    private HashMap<String, TreeNode> root;

    public Tree(HashMap<String, TreeNode> root) {
        this.root = root;
    }

    /**
     *Insert a command into the tree.
     * @param template The template of the command.
     * @param command The command to insert.
     */
    public void insert(String template, Command command ) {
        String[] path = template.split("/");
        if(!root.containsKey(path[0]))
            root.put(path[0], new TreeNode());

        insertCommand(Arrays.copyOfRange(path, 1, path.length), command, root.get(path[0]));
    }

    private void insertCommand(String[] strings, Command c, TreeNode root) {
        final TreeNode nextChild;

        if(root.children == null || !root.children.containsKey(strings[0])) {
            if(root.children==null) {
                root.children = new HashMap<>();
            }
            nextChild = new TreeNode();
            root.children.put(strings[0], nextChild);
        } else{
            nextChild = root.children.get(strings[0]);
        }

        if(strings.length==1) {
            root.children.get(strings[0]).setCommand(true);
            root.children.get(strings[0]).setElem(c);
            return;
        } else  {
            insertCommand(Arrays.copyOfRange(strings, 1, strings.length), c, nextChild);
        }

    }

    /**
     *Search through the tree for a command.
     * @param rq
     * @return
     */
    public Command search(Request rq) {
        TreeNode method = root.get(rq.getMethod());
        String[] split = rq.getPath().split("/");

        return searchFor(rq, Arrays.copyOfRange(split, 1, split.length), method);
    }

    private Command searchFor(Request rq, String[] path,  TreeNode root) {
        if(path.length == 0) {
            if(root.isCommand()) {
                return root.getElem();
            }
        }

        String curr = path[0];

        if(path[0].matches("\\d{1,}")) {
            String id = getKeyByValue(root);
            rq.addParameters(id,curr);
            curr = id;
        }

        return searchFor(rq, Arrays.copyOfRange(path, 1, path.length), root.children.get(curr));
    }

    /**
     *
     * @param root
     * @return
     */
    private String getKeyByValue(TreeNode root) {
        for (Map.Entry<String, TreeNode> entry: root.children.entrySet()) {
            if(entry.getKey().matches("\\{\\wid\\}")) {
                return entry.getKey();
            }
        }
        return null;
    }
}
