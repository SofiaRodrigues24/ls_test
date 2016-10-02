package pt.isel.ls;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.*;

/**
 * Created by tiago on 29-Sep-16.
 */
public class JDBC {
    public static Connection con = null;
    public static ResultSet dataBase() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("ls");
        ds.setPassword("Tiago1994");
        ds.setUser("sa");

        try {

            con = ds.getConnection();
            System.out.println("connection works");

            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM students");
            //st.executeUpdate("INSERT INTO students values ('Tiago')");
            //st.executeUpdate("INSERT INTO students values ('Sofia')");
            //st.executeUpdate("INSERT INTO students values ('Jose')");

            //st.executeUpdate("UPDATE students set Name = 'Joao' WHERE Name = 'Tiago'");
            st.executeUpdate("Delete FROM students Where Name = 'Jose'");
            ResultSet res = null;
            //res = st.executeQuery("SELECT Name FROM students");

            st.close();
            con.close();
            return res;


        }catch (SQLException e){
            //throw new SQLException()
        }
        return null;
    }

    public static Statement dataBaseInsert(String name) throws SQLException{
        SQLServerDataSource ds = new SQLServerDataSource();
        //ds.setServerName();
        ds.setDatabaseName("ls");
        ds.setPassword("Tiago1994");
        ds.setUser("sa");

        con = ds.getConnection();
        Statement st = con.createStatement();

         st.executeUpdate("INSERT INTO students values ('"+name+"')");


        return st;

    }

    public static ResultSet dataBaseSelect(String name) throws SQLException{

        SQLServerDataSource ds = new SQLServerDataSource();
        //ds.setServerName();
        ds.setDatabaseName("ls");
        ds.setPassword("Tiago1994");
        ds.setUser("sa");
        con = ds.getConnection();
        Statement st = con.createStatement();
        ResultSet res = null;
        res = st.executeQuery("SELECT Name FROM students WHERE Name = '"+name+"'");

        return  res;
    }

    public static  Statement dataBaseUpdate(String toUpdate, String old) throws SQLException{
        SQLServerDataSource ds = new SQLServerDataSource();
        //ds.setServerName();
        ds.setDatabaseName("ls");
        ds.setPassword("Tiago1994");
        ds.setUser("sa");

        con = ds.getConnection();
        ResultSet res;
        Statement sUpdate = con.createStatement();
        sUpdate.executeUpdate("UPDATE students set Name = '"+ toUpdate + "' WHERE Name = '"+ old +"'");
        //con.close();
        return sUpdate;
    }
    public  static  Statement dataBaseDelete(String name) throws  SQLException{
        SQLServerDataSource ds = new SQLServerDataSource();
        //ds.setServerName();
        ds.setDatabaseName("ls");
        ds.setPassword("Tiago1994");
        ds.setUser("sa");

        con = ds.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("Delete FROM students Where Name = '" + name +"'");
        return  st;
    }
}
