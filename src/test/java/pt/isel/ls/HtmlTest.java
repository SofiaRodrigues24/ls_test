package pt.isel.ls;

import org.junit.Test;
import pt.isel.ls.domain.CheckList;
import pt.isel.ls.representation.html.HtmlFile;
import pt.isel.ls.representation.html.HtmlObject;
import pt.isel.ls.representation.html.HtmlTable;


import java.io.IOException;

/**
 * Created by tiago on 11-Nov-16.
 */
public class HtmlTest {
    @Test
    public void getHtmlObjectFromChecklist()throws IOException{
        CheckList c = new CheckList(1, "html", "htmlTest");
        HtmlObject htmlObject  = c.getHtml();
        String s = htmlObject.toString();
        HtmlFile file = new HtmlFile("test");
        HtmlTable table = new HtmlTable(null);
        System.out.println(s);




    }
}
