package by.company.DAO;

/**
 * It`s interface for users DAO class
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface UsersDAO {
    void createUser() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException;
    int loginUser() throws SQLException;
    int check_info_size(int id) throws SQLException;

    void setSize(int size, int id) throws SQLException;
}
