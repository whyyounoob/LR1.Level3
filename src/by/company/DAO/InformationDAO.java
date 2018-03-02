package by.company.DAO;

import java.sql.SQLException;

/**
 * It`s interface for Information DAO Class
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 03.03.2018
 */

public interface InformationDAO {
    public void setInfo(final String path, final String date, final String type) throws SQLException;
}
