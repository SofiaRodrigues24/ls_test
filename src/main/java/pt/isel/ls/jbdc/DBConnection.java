package pt.isel.ls.jbdc;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;


public class DBConnection {

    private static final String INSTANCE_NAME = "SQL_IN";
    private static final String USER = "SQL_USER";
    private static final String PASSWORD = "SQL_PASS";
    private static final String DATABASE_NAME = "SQL_DB";
    private static final String SERVER_NAME = "SQL_SERVER";

    private SQLServerDataSource dataSource;
    private Connection connection;

    public DBConnection(SQLServerDataSource dataSource) {
        this.dataSource = dataSource;
        setCredentials();
    }

    public void setCredentials() {
        dataSource.setInstanceName(System.getenv(INSTANCE_NAME));
        dataSource.setUser(System.getenv(USER));
        dataSource.setPassword(System.getenv(PASSWORD));
        dataSource.setDatabaseName(System.getenv(DATABASE_NAME));
        dataSource.setServerName(System.getenv(SERVER_NAME));
    }


    public SQLServerDataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws SQLServerException {
        return connection = dataSource.getConnection();
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
