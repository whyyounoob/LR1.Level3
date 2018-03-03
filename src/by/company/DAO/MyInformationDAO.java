package by.company.DAO;
/**
 * This class is used to work with information about files in the database
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */
import java.sql.*;

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

    public void getInfo() throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLConstants.SELECT_INFO);
        while(resultSet.next()){

        }
    }
}
