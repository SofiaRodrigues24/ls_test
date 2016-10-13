package pt.isel.ls.jbdc;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;


public class DBConnection {

    private SQLServerDataSource dataSource;

    public DBConnection(SQLServerDataSource dataSource) {
        this.dataSource = dataSource;
        setCredentials();
    }

    public void setCredentials() {
        dataSource.setInstanceName(System.getenv("SQL_IN"));
        dataSource.setUser(System.getenv("SQL_USER"));
        dataSource.setPassword(System.getenv("SQL_PASS"));
        dataSource.setDatabaseName(System.getenv("SQL_DB"));
        dataSource.setServerName(System.getenv("SQL_SERVER"));
    }


    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
