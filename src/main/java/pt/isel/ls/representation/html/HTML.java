package pt.isel.ls.representation.html;


public class HTML implements HtmlValue {
    private String name, value;
    public HTML(String name , String value){
        this.name = name;
        this.value = value;
    }



    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public HTML getHtmlObj() {
        return null;
    }
}
