package pt.isel.ls.representation.html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiago on 11-Nov-16.
 */
public class HtmlArray implements HtmlValue {
    private List<HtmlValue> values;

    public HtmlArray(){
        values = new ArrayList<>();
    }

    public void add(String name,int value){
        values.add(new HtmlNumber(name,value));
    }

    public void add(String name,String value){
        values.add(new HtmlString(name,value));
    }

    public void add(HtmlValue value){
        values.add(value);
    }

    @Override
    public HTML getHtmlObj() {
        return null;
    }
}
