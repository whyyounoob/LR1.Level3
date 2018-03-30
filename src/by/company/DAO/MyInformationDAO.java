package by.company.DAO;

import by.company.LOGIC.Item;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to work with information about files in the database.
 *
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

public class MyInformationDAO implements InformationDAO {

    @Override
    public void setInfo(final String path, final String date, final String type)
            throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.INSERT_INFO);
        preparedStatement.setString(1, path);
        preparedStatement.setString(2, date);
        preparedStatement.setString(3, type);
        preparedStatement.execute();
        preparedStatement.close();
        connection.close();
    }


    @Override
    public List<Item> getInfo(final String neededType) throws SQLException {
        List<Item> list = new ArrayList<Item>();
        Connection connection = new MyDAOFactory().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLConstants.SELECT_INFO);
        statement.setString(1, neededType);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            File file = new File(resultSet.getString(1));
            if (file.exists()) {
                Item item = new Item(file, neededType, resultSet.getString(2));
                list.add(item);
            }
        }
        resultSet.close();
        statement.close();
        connection.close();
        return list;
    }

    @Override
    public void removeInfo(final String path) throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();
        PreparedStatement statement = connection.prepareStatement(SQLConstants.DELETE_INFO);
        statement.setString(1, path);
        statement.execute();
        statement.close();
        connection.close();
    }


}
