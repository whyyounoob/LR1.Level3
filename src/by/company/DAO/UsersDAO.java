package by.company.DAO;

/**
 * It`s interface for users DAO class
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

import java.sql.SQLException;

public interface UsersDAO {
    void createUser() throws SQLException;
    int loginUser() throws SQLException;
}
