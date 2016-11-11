package pt.isel.ls.representation.html;

/**
 * Created by tiago on 10-Nov-16.
 */
public class HtmlBoolean implements HtmlValue {
    private HTML obj;
    public HtmlBoolean(String name,boolean completed) {
        obj = new HTML(name, ""+ completed);
    }


    @Override
    public HTML getHtmlObj() {
        return obj;
    }

    public String toString(){
        return obj.getName() + " " + obj.getValue();
    }
}
