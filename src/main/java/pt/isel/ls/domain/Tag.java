package pt.isel.ls.domain;

public class Tag {
    private int gid;
    private String name;
    private int color;


    public Tag(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public Tag(int gid, String name, int color) {
        this(name, color);
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "\n\t\tLID: "+ gid +"\n\t\tname: "+name+ "\n\t\tcolor: "+color+"\n";
    }
}
