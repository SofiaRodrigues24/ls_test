package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import pt.isel.ls.jbdc.DBConnection;
import pt.isel.ls.manager.Result;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


public interface Command {
    Result execute(Connection con, HashMap<String, String> map) throws SQLException;
    //TODO : usar con.setAutoCommit(false) para ass transações no execute
    //TODO: Statement generatedKey retorna a chave gerada

    default DBConnection getConnection() {
        return new DBConnection(new SQLServerDataSource());
    }
}