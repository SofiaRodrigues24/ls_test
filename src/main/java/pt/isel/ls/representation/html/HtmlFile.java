package pt.isel.ls.representation.html;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tiago on 03-Nov-16.
 */
public class HtmlFile {
    protected BufferedWriter writer;
    private String title;
    ArrayList<HtmlTable> tables;
    private boolean isStarted = false;

    StringBuilder sb;

    public HtmlFile(String filename) throws IOException {
        writer = new BufferedWriter(new FileWriter(filename+".html"));
        title = filename;
        tables = new ArrayList<>();

    }
    public HtmlFile(){
        tables = new ArrayList<>();
        sb = new StringBuilder();
    }

    public BufferedWriter getFile(){
        return writer;
    }
    public void addTable(HtmlTable table){
        tables.add(table);
    }
    protected void startHtml() throws IOException {
        if(writer!=null){
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html lang=en>\n");
            writer.write("<head> \n");
            writer.write("<title> " + title + "</title>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");
        }else {
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html lang=en>\n");
            sb.append("<head> \n");
            sb.append("<title> " + title + "</title>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
        }
    }

    public void print() throws IOException{
        startHtml();
        for(HtmlTable table : tables){
            if(writer!=null){
                table.print(writer);
            }else{
                sb.append(table.getTable());
            }


        }
        closeFile();
    }

    private void closeFile() throws IOException {
        if(writer!=null){
            writer.write("</body>\n");
            writer.write("</html>");
            writer.close();
        }else{
            sb.append("</body>\n");
            sb.append("</html>");
        }


    }

    public String getFileString() throws IOException {
        return sb.toString();
    }
}
