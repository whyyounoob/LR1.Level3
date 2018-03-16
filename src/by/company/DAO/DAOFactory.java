package by.company.DAO;



import java.sql.Connection;
import java.sql.SQLException;

/**
 * It`s interface for main DAO class
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */
public interface DAOFactory {

    Connection getConnection()throws SQLException;

}
