package by.company.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface DAOFactory {
    public Connection getConnection()throws SQLException;
    public void closeConnection() throws SQLException;
}
