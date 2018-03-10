package by.company.DAO;
/**
 * This class is used to work with information about files in the database
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */
import by.company.LOGIC.AddItem;
import by.company.LOGIC.Item;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyInformationDAO implements InformationDAO {
    /**
     * This method for storing information about a file in a database
     * @param date - Date of recording information in the database
     * @param path - the path to the file
     * @param type - type of the file
     */
    @Override
    public void setInfo(final String path, final String date, final String type) throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.INSERT_INFO);
        preparedStatement.setString(1, path);
        preparedStatement.setString(2, date);
        preparedStatement.setString(3, type);
        preparedStatement.execute();
        connection.close();
    }

    public List<Item> getInfo(String needed_type) throws SQLException {
        List<Item> list = new ArrayList<Item>();
        Connection connection = new MyDAOFactory().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_INFO);
        statement.setString(1,needed_type);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            File file = new File(resultSet.getString(1));
            if(file.exists()){
                Item item = new Item(file, needed_type, resultSet.getString(2));
                list.add(item);
            }
        }
        return list;
    }


}
