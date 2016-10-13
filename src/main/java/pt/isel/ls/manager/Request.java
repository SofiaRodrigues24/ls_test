package pt.isel.ls.manager;


import java.util.HashMap;

public class Request {

    private String args;
    private String method;
    private String path;
    private HashMap<String, String> parameters;
    private HashMap<String, String> pairs_id;

    public Request(String [] args) {
        this.args = args[0].concat(" "+args[1]);
        this.method = args[0];
        this.path = createPath(args[1]);
        this.parameters = createParameters(args[1]);
        this.pairs_id = createIds();
    }

    private HashMap<String, String> createIds() {
        HashMap<String, String> map = new HashMap<>();

        map.put("checklists", "cid");
        map.put("templates", "tid");
        map.put("tasks", "lid");

        return map;
    }

    private HashMap<String, String> createParameters(String arg) {
        HashMap<String, String> map = new HashMap<>();

        String regex = ".*\\d{1,}.*";
        if(path.matches(regex))
            getParametersInPath(map);

        if(arg.contains("=")) {
            String substring = arg.substring(arg.lastIndexOf("/")+1);
            String[] split = substring.replace("+"," ").split("&");
            for (String s:split) {
                map.put(s.substring(0, s.lastIndexOf("=")), s.substring(s.indexOf("=")+1));
            }
        }
        return map;
    }

    private void getParametersInPath(HashMap<String, String> map) {
        String[] split = path.split("/");
        for (int i = 0; i< split.length; ++i) {
            if(split[i].matches("\\d{1,}")) {
                String strId = getId(split[i-1]);
                map.put(strId, split[i]);
            }
        }
    }

    private String getId(String s) {
        return pairs_id.get(s);
    }

    private String createPath(String arg) {
        if(arg.contains("="))
            return arg.substring(0, arg.lastIndexOf("/"));
        return arg;
    }

    public String getArgs() {
        return args;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

}
