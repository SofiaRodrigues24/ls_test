package pt.isel.ls;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.*;

/**
 * Created by tiago on 29-Sep-16.
 */
public class TestDataBase {

    @Test
    public void dataBaseInsertTest() throws SQLException {
        String name = "Telmo", check;
        Statement st= JDBC.dataBaseInsert(name);
        ResultSet rs = st.executeQuery("SELECT Name FROM students WHERE Name = '"+name+"'");
        boolean equals = false;
        while(rs.next()){
            check = rs.getString("Name");
            if(check.equals(name)) {
                equals = true;
                break;
            }

        }
        assertTrue(equals);
    }

    @Test
    public void dataBaseSelectTest() throws SQLException{
        String name = "Sofia", check;
        ResultSet res = JDBC.dataBaseSelect(name);
        boolean eq = false;
        while (res.next()){
            check = res.getString("Name");
            if(name.equals(check))
                eq = true;

        }

        assertTrue(eq);



    }

    @Test
    public void dataBaseUpdateTest() throws SQLException{
        String old = "Mario", update = "Tiago";
        String check;
        Statement st = JDBC.dataBaseUpdate(update, old);
        boolean eq = false;
        ResultSet res = st.executeQuery("SELECT Name FROM students");
        while(res.next()) {
            check = res.getString("Name");
            if (check.equals(update)) {
                eq = true;
                break;
            }
        }
        assertTrue(eq);
    }

    @Test
    public void dataBaseDeleteTest() throws SQLException{
        String name = "Telmo", check;
        Statement st = JDBC.dataBaseDelete(name);
        ResultSet res = st.executeQuery("SELECT Name FROM students");
        boolean del = true;
        while (res.next()){
            check  = res.getString("Name");
            if(name.equals(check))
                del = false;
        }
        assertTrue(del);
    }
}
