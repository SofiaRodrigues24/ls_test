package pt.isel.ls.commands;

import org.junit.Test;
import pt.isel.ls.domain.*;
import pt.isel.ls.representation.html.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by tiago on 11-Nov-16.
 */
public class HtmlTest {
    @Test
    public void getHtmlObjectFromChecklist()throws IOException{
        CheckList c = new CheckList(1, "html", "htmlTest");
        HtmlObject htmlObject  = c.getHtml();
        String s = htmlObject.toString();
        HtmlFile file = new HtmlFile();
        int size = htmlObject.getValues().size();
        String[] colNames = new String[size];
        String[] data = new String[size];
        int i = 0;
        for(HtmlValue val : htmlObject.getValues()){
            colNames[i] = val.getHtmlObj().getName();
            data[i] = val.getHtmlObj().getValue();
            i++;
        }
        HtmlTable table = new HtmlTable(colNames);
        table.addRow(data);
        file.addTable(table);
        file.print();

        String ret = file.getFileString();
        String cmp = "<!DOCTYPE html>\n" +
                "<html lang=en>\n" +
                "<head> \n" +
                "<title> null</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "<tr>\n" +
                "<th>cid</th>\n" +
                "<th>name</th>\n" +
                "<th>description</th>\n" +
                "<th>isClosed</th>\n" +
                "<th>duedate</th>\n" +
                "<tr>\n" +
                "<td>"+c.getCid()+"</td> <td>"+c.getName()+"</td> <td>"+c.getDescription()+"</td> <td>"+c.getCompleted()+"</td> <td>"+c.getDuedate()+"</td> \n" +
                "</table></body>\n" +
                "</html>";

        assertEquals(ret,cmp);        //colocar os asserts

    }
    @Test
    public void getHtmlObjectFromTemplate() throws IOException {
        Template t = new Template(1,"html","html Test for Template");
        HtmlObject html = t.getHtml();
        HtmlFile file = new HtmlFile();
        int size = html.getValues().size();
        String[] colNames = new String[size];
        String[] data = new String[size];
        int i = 0;
        for(HtmlValue val : html.getValues()){
            colNames[i]=val.getHtmlObj().getName();
            data[i]=val.getHtmlObj().getValue();
            i++;
        }
        HtmlTable table = new HtmlTable(colNames);
        table.addRow(data);
        file.addTable(table);
        file.print();

        String ret = file.getFileString();

        String cmp = "<!DOCTYPE html>\n" +
                "<html lang=en>\n" +
                "<head> \n" +
                "<title> null</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "<tr>\n" +
                "<th>tid</th>\n" +
                "<th>name</th>\n" +
                "<th>description</th>\n" +
                "<tr>\n" +
                "<td>"+t.getTid()+"</td> <td>"+t.getName()+"</td> <td>"+t.getDescription()+"</td> \n" +
                "</table></body>\n" +
                "</html>";

        assertEquals(ret,cmp);
    }
    @Test
    public void getHtmlObjectFromTask() throws IOException{
        Task task = new Task(1, "html", "html test for Task");
        HtmlObject ho = task.getHtml();
        HtmlFile file = new HtmlFile();
        int size = ho.getValues().size();
        String[] colNames = new String[size];
        String[] data = new String[size];
        int i = 0;
        for(HtmlValue val: ho.getValues()){
            colNames[i]=val.getHtmlObj().getName();
            data[i]=val.getHtmlObj().getValue();
            i++;
        }
        HtmlTable table = new HtmlTable(colNames);
        table.addRow(data);
        file.addTable(table);
        file.print();


        String cmp = "<!DOCTYPE html>\n" +
                "<html lang=en>\n" +
                "<head> \n" +
                "<title> null</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "<tr>\n" +
                "<th>lid</th>\n" +
                "<th>name</th>\n" +
                "<th>description</th>\n" +
                "<th>isClosed</th>\n" +
                "<th>duedate</th>\n" +
                "<tr>\n" +
                "<td>"+task.getLid()+"</td> <td>"+task.getName()+"</td> <td>"+task.getDescription()+"</td> <td>"+task.isClosed()+"</td> <td>"+task.getDuedate()+"</td> \n" +
                "</table></body>\n" +
                "</html>";

        assertEquals(cmp, file.getFileString());
    }

    @Test
    public void getHtmlObjectFromTag() throws IOException{
        Tag t = new Tag(1,"TAG", 2);

        HtmlObject ho = t.getHtml();
        HtmlFile file = new HtmlFile();
        int size = ho.getValues().size();
        String[] colNames = new String[size];
        String[] data = new String[size];
        int i = 0;
        for(HtmlValue val: ho.getValues()){
            colNames[i]=val.getHtmlObj().getName();
            data[i]=val.getHtmlObj().getValue();
            i++;
        }
        HtmlTable table = new HtmlTable(colNames);
        table.addRow(data);
        file.addTable(table);
        file.print();



        String cmp = "<!DOCTYPE html>\n" +
                "<html lang=en>\n" +
                "<head> \n" +
                "<title> null</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "<tr>\n" +
                "<th>gid</th>\n" +
                "<th>name</th>\n" +
                "<th>color</th>\n" +
                "<tr>\n" +
                "<td>"+t.getGid()+"</td> <td>"+t.getName()+"</td> <td>"+t.getColor()+"</td> \n" +
                "</table></body>\n" +
                "</html>";

        assertEquals(file.getFileString(),cmp);


    }

    @Test
    public void getHtmlObjectFromCollection(){
        //TODO: por acabar
        CheckList c1 = new CheckList(1, "test1", "HtmlTest1");
        CheckList c2 = new CheckList(2, "test2", "HtmlTest2");

        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(c1);
        checkLists.add(c2);

        Collections<CheckList> list = new Collections<>("checklist", checkLists);

        HtmlObject obj = list.getHtml();
        //System.out.println(obj.toString());

        HtmlFile file = new HtmlFile();

        ArrayList<HtmlValue> list1 = obj.getValues();

        for(HtmlValue v : list1){

            System.out.println(v.toString());
        }

    }

}
