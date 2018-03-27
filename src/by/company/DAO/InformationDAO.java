package by.company.DAO;

import by.company.LOGIC.Item;

import java.sql.SQLException;
import java.util.List;

/** It`s interface for Information DAO Class.
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 03.03.2018
 */

public interface InformationDAO {

    /** This method creates a row in the "information" table,
     * which contains the path to the file, the upload date, and the file type.
     * @param path - path to the file
     * @param date - upload date
     * @param type - the file type
     * @throws SQLException if SQLITE_CONSTRAINT_UNIQUE this file is already contained
     */

    void setInfo(String path, String date, String type) throws SQLException;
    /** This method returns a list of files of the required type.
     * @param neededType - type of file
     * @throws SQLException can be lost connection
     * @return list of file
     */
    List<Item> getInfo(String neededType) throws SQLException;
    /** This method delete file from table.
     * @param path - path to the file
     * @throws SQLException can be lost connection
     */
    void removeInfo(String path) throws SQLException;

}
