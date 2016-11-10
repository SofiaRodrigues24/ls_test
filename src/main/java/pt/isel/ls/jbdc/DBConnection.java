package pt.isel.ls.jbdc;

import com.microsoft.sqlserver.jdbc.ISQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;


public class DBConnection {

    private static String variable;
    private static String serverName;
    private static String databaseName;
    private static String user;
    private static String password;
    private static SQLServerDataSource dataSource;


    public static void init(String s) {
        variable = s;
        setCredentials();
        setDataSource();
    }

    public static void setDataSource() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(serverName);
        dataSource.setDatabaseName(databaseName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
    }

    public static void setCredentials() {
        String getenv = System.getenv(variable);

        String[] split = getenv.split(";");
        for (int i = 0; i < split.length; i++) {
            setField(i, split[i].split("=")[1]);
        }
    }

    private static void setField(int idx, String s) {
        switch (idx) {
            case 0:
                serverName = s;
                break;
            case 1:
                databaseName = s;
                break;
            case 2:
                user = s;
                break;
            case 3:
                password = s;
                break;
        }
    }

    public static Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void disconnect(Connection con) throws SQLException {
        con.close();
    }


}
