package pt.isel.ls;

import org.junit.Test;
import pt.isel.ls.domain.*;
import pt.isel.ls.representation.html.HTML;
import pt.isel.ls.representation.html.HTMLChecklist;
import pt.isel.ls.representation.html.HTMLTemplate;
import pt.isel.ls.representation.html.HTMLWriter;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Sonia on 12-11-2016.
 */
public class HtmlTest {


    @Test
    public void HtmlTestTemplate(){
        Template  t = new Template(1,"test","test html for Template");
        HTMLTemplate temp = new HTMLTemplate(t);
        HTMLWriter htmlWriter = new HTMLWriter();
        temp.write(htmlWriter);


        String exp = "<!DOCTYPE html><html><body><h1>TEMPLATE "+t.getTid()+"</h1><h3>Name: "+t.getName()+"</h3><h3>Desceription: "+t.getDescription()+"</h3><table border = \"1\" width=\"300\" height=\"150\"><tr><th>id</th><th>name</th><th>description</th></tr></table></body></html>";
        assertEquals(temp.toString(),exp);

    }
    @Test
    public void HtmlTestChecklist(){
        CheckList c = new CheckList(1,"test1","test html for Checklist");
        HTMLChecklist checklist = new HTMLChecklist(c);
        Task task = new Task(1,"htmlTask1","test Html for CheckList with Tasks");
        Task task2 = new Task(2,"htmlTask2","test Html for CheckList with Tasks 2");
        c.addTask(task);
        c.addTask(task2);


        HTMLWriter htmlWriter = new HTMLWriter();
        checklist.write(htmlWriter);

        String s =checklist.toString();
        String expected = "<!DOCTYPE html><html><body><h1>CHECKLIST "+c.getCid()+"</h1><h3>Name: "+c.getName()+"</h3><h3>Description: "+c.getDescription()+"</h3><h3>Completed: "+c.getCompleted()+"</h3><table border = \"1\" width=\"300\" height=\"150\"><tr><th>id</th><th>name</th><th>description</th></tr><tr><td>"+task.getLid()+"</td><td>"+task.getName()+"</td><td>"+task.getDescription()+"</td></tr><tr><td>"+task2.getLid()+"</td><td>"+task2.getName()+"</td><td>"+task2.getDescription()+"</td></tr></table><table border = \"1\" width=\"300\" height=\"150\"><tr><th>id</th><th>name</th><th>description</th></tr></table></body></html>";

        assertNotNull(htmlWriter);
        assertNotNull(checklist);
        assertEquals(expected,s);


    }

    @Test
    public void HtmlTestCollectionsChecklist(){
        CheckList c1 = new CheckList(2,"test_check_col","test html for Collection Checklist");
        CheckList c2 = new CheckList(3,"tes_col_check","test htm for Collection Checklist");

        List<CheckList> checkLists = new ArrayList<>();
        checkLists.add(c1);
        checkLists.add(c2);
        HTMLWriter writer = new HTMLWriter();

        Collections<CheckList> list = new Collections<>("checklist", checkLists);

        HTML html = list.getHTML();
        html.write(writer);
        String s = html.toString();



        String exp = "<!DOCTYPE html><html><body><table border = \"1\" width=\"300\" height=\"150\"><td colspan = \"3\">CHECKLISTS</td><tr><th>id</th><th>name</th><th>description</th></tr><tr><td>"+c1.getCid()+"</td><td>"+c1.getName()+"</td><td>"+c1.getDescription()+"</td></tr><tr><td>"+c2.getCid()+"</td><td>"+c2.getName()+"</td><td>"+c2.getDescription()+"</td></tr></table></body></html>";
        assertNotNull(html);
        assertNotNull(writer);
        assertEquals(exp,s);
    }
    @Test
    public void HtmlTestCollectionTemplate(){
        Template t1 = new Template(1,"temp1","Test html for Collection Template ");
        Template t2 = new Template(2,"temp2","Test html for Col_Template");
        List<Template> templates = new ArrayList<>();
        templates.add(t1);
        templates.add(t2);
        HTMLWriter htmlWriter = new HTMLWriter();

        Collections<Template> c_template = new Collections<>("template", templates);
        HTML html = c_template.getHTML();
        html.write(htmlWriter);
        String s = html.toString();


        String expected = "<!DOCTYPE html><html><body><table border = \"1\" width=\"300\" height=\"150\"><td colspan = \"3\">TEMPLATES</td><tr><th>id</th><th>name</th><th>description</th></tr><tr><td>"+t1.getTid()+"</td><td>"+t1.getName()+"</td><td>"+t1.getDescription()+"</td></tr><tr><td>"+t2.getTid()+"</td><td>"+t2.getName()+"</td><td>"+t2.getDescription()+"</td></tr></table></body></html>";

        assertNotNull(html);
        assertNotNull(htmlWriter);
        assertEquals(expected,s);
    }
    @Test
    public void HtmlTestCollectionTag(){
        Tag t = new Tag(1,"tag1",1);
        Tag t1= new Tag(2,"tag2",2);
        List<Tag> tags= new ArrayList<>();
        tags.add(t);
        tags.add(t1);
        HTMLWriter hWriter = new HTMLWriter();

        Collections<Tag> col_tags = new Collections<>("tag",tags);
        HTML h = col_tags.getHTML();
        h.write(hWriter);
        String s = h.toString();


        String result ="<!DOCTYPE html><html><body><table border = \"1\" width=\"300\" height=\"150\"><td colspan = \"3\">TAGS</td><tr><th>id</th><th>name</th><th>color</th></tr><tr><td>"+t.getGid()+"</td><td>"+t.getName()+"</td><td>"+t.getColor()+"</td></tr><tr><td>"+t1.getGid()+"</td><td>"+t1.getName()+"</td><td>"+t1.getColor()+"</td></tr></table></body></html>";
        assertNotNull(h);
        assertNotNull(hWriter);
        assertEquals(result,s);
    }
}