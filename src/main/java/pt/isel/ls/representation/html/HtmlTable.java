package pt.isel.ls.representation.html;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tiago on 03-Nov-16.
 */
public class HtmlTable  {
    private String[] colNames;
    private ArrayList<String[]> dataRow;
    private String paper="";
    private StringBuilder sb;
    private BufferedWriter writer;

    public HtmlTable(String[] colNames) throws IOException {
        this.colNames = colNames;
        dataRow = new ArrayList<>();
        sb = new StringBuilder();
    }





    public void addRow(String[] data){
        dataRow.add(data);
    }


    private void initTable() throws IOException {
        //writer.write("<table>");
        sb.append("<table>\n");
    }

    public void print(BufferedWriter writer) throws IOException {
        this.writer = writer;
        initTable();
        writeColNames(colNames);
        for(String[] s : dataRow){
            writeRow(s);
        }

        writer.write("</table>");

    }

    public void print() throws IOException {

        initTable();
        writeColNames(colNames);
        for(String[] s : dataRow){
            writeRow(s);
        }

        sb.append("</table>");

    }

    private void writeColNames(String[] colNames) throws IOException {
        //writer.write("<tr>");
        sb.append("<tr>\n");
        for(int i = 0; i<colNames.length; i++){
            writeName(colNames[i]);
        }
        //writer.write("</tr>");
        //writer.newLine();
    }
    private void writeName(String colName) throws IOException {
        //writer.write("<th>"+colName+"</th>");
        sb.append("<th>"+colName+"</th>\n");
        //writer.newLine();
    }

    private void writeRow(String[] s) throws IOException {
        //writer.write("<tr>");
        sb.append("<tr>\n");
        for (int i = 0; i<s.length; i++){
          //  writer.write("<td>"+s[i]+"</td> ");
            sb.append("<td>"+s[i]+"</td> ");
        }
        //writer.write("</tr>");
        sb.append("\n");
    }

    public String getTable() throws IOException {
        print();
        paper = sb.toString();
        return paper;
    }


}
