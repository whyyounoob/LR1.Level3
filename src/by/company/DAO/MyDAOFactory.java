package by.company.DAO;

/**
 * This main DAO class create connection
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MyDAOFactory implements DAOFactory {
    private Connection connection;
    @Override
    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:D:\\javaproject\\LR1.Level3\\src\\by\\company\\LR3Level1.db");
        return connection;
    }

}
