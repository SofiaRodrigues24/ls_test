package pt.isel.ls.manager;

import java.util.HashMap;

public class Request {

    private String method;
    private String path;
    private HashMap<String, String> parameters;
    private HashMap<String, String> header;

    public Request(String str) {
        String[] args = str.split(" ");
        this.method = args[0];
        if(args.length>1) {
            this.path = createPath(args[1]);
            this.parameters = createParameters(args[1]);
            if (args.length > 2)
                this.header = createHeaderOptions(args[2]);
        }
    }

    private HashMap<String, String> createHeaderOptions(String header) {
        HashMap<String, String> options = new HashMap<>();

        String[] split = header.split("\\|");
        for (String s: split) {
            String[] pair = s.split(":");
            options.put(pair[0], pair[1]);
        }

        return options;
    }


    private HashMap<String, String> createParameters(String arg) {
        HashMap<String, String> map = new HashMap<>();

        if(arg.contains("=")) {
            String substring = arg.substring(arg.lastIndexOf("/")+1);
            String[] split = substring.replace("+"," ").split("&");
            for (String s:split) {
                map.put(s.substring(0, s.lastIndexOf("=")), s.substring(s.indexOf("=")+1));
            }
        }
        return map;
    }

    private String createPath(String arg) {
        if(arg.contains("="))
            return arg.substring(0, arg.lastIndexOf("/"));
        return arg;
    }


    public void addParameters(String key, String value) {
        parameters.put(key, value);
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

    public HashMap<String, String> getHeader() {
        return header;
    }
}
