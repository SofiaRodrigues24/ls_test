package pt.isel.ls.representation.html;

/**
 * Created by tiago on 10-Nov-16.
 */
public class HtmlNumber implements HtmlValue {
    private int number;
    private HTML obj;
    public HtmlNumber(String name, int number) {
        this.number = number;
        obj = new HTML(name, Integer.toString(number));

    }


    @Override
    public HTML getHtmlObj() {
        return obj;
    }

    public String toString(){
        return obj.getName() + " " + obj.getValue();
    }
}
