package pt.isel.ls.representation.html;

/**
 * Created by tiago on 10-Nov-16.
 */
public class HtmlString implements HtmlValue {
    private HTML obj;
    public HtmlString(String name, String value) {
        obj = new HTML(name, value);
    }

    @Override
    public HTML getHtmlObj() {
        return obj;
    }

    public String toString(){
        return obj.getName() + " " + obj.getValue();
    }
}
