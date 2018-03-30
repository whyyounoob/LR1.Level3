package by.company.DAO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

/**
 * It`s interface for users DAO class.
 *
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

public interface UsersDAO {

    /**
     * This method returns a list of files of the required type.
     *
     * @throws SQLException             - incorrect input data, lost connection
     * @throws NoSuchAlgorithmException - haven`t algorithm
     * @throws InvalidKeySpecException  - invalid key
     */

    void createUser() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException;

    /**
     * This method returns id of user.
     *
     * @return id of user
     * @throws SQLException - incorrect input data, lost connection
     */

    int loginUser() throws SQLException;

    /**
     * This method returns the maximum file size that the user can add.
     *
     * @param id - id user
     * @return file size(byte)
     * @throws SQLException - incorrect input data, lost connection
     */

    int checkInfoSize(int id) throws SQLException;

    /**
     * This method update about file size of user.
     *
     * @param id - id user
     * @param size - file size
     * @throws SQLException - incorrect input data, lost connection
     */

    void setSize(int size, int id) throws SQLException;
}
