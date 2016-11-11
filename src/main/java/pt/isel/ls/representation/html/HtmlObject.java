package pt.isel.ls.representation.html;

import java.util.ArrayList;

/**
 * Created by tiago on 10-Nov-16.
 */
public class HtmlObject implements HtmlValue {

    private ArrayList<HtmlValue> values;
    public HtmlObject(){
        values = new ArrayList<>();
    }


    public void add(HtmlValue val){
        if(values!=null){
            values.add(val);
        }
    }

    public ArrayList<HtmlValue> getValues(){
        return values;
    }

    @Override
    public HTML getHtmlObj() {
        return null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(HtmlValue val : values){
            sb.append(val.toString()+"\n");
        }

        return sb.toString();
    }
}
