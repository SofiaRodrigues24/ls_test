package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import pt.isel.ls.jbdc.DBConnection;

import java.sql.SQLException;
import java.util.HashMap;


public interface Command {
    void execute(HashMap<String, String> map) throws SQLException;
    String getRegularExpression();
    //TODO : usar con.setAutoCommit(false) para ass transações no execute
    //TODO: Statement generatedKey retorna a chave gerada

    void print();

    default DBConnection getConnection() {
        return new DBConnection(new SQLServerDataSource());
    }
}
