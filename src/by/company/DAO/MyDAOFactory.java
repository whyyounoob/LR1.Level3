package by.company.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDAOFactory implements DAOFactory {
    private Connection connection;
    @Override
    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("D:\\javaproject\\LR1.Level3\\src\\by\\company\\LR3Level1");
        return connection;
    }

    @Override
    public void closeConnection() throws SQLException {
        connection.close();
    }

}
