package by.company.DAO;

import by.company.LOGIC.Item;

import java.sql.SQLException;
import java.util.List;

/**
 * It`s interface for Information DAO Class
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 03.03.2018
 */

public interface InformationDAO {
    void setInfo(String path, final String date, final String type) throws SQLException;
    List<Item> getInfo(String needed_typ) throws SQLException;
    void removeInfo(String path) throws SQLException;

}
