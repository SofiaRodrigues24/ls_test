package pt.isel.ls.representation.html;


import java.util.Date;

/**
 * Created by tiago on 10-Nov-16.
 */
public class HtmlDuedate implements HtmlValue {
    private HTML obj;
    public HtmlDuedate(String name, Date date) {
        obj = new HTML(name, ""+date);
    }


    @Override
    public HTML getHtmlObj() {
        return obj;
    }

    public String toString(){
        return obj.getName() + " " + obj.getValue();
    }
}
