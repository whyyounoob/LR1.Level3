package by.company.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyInformationDAO implements InformationDAO {
    @Override
    public void setVideo(String path, String date, String type) throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.INSERT_INFO);
        preparedStatement.setString(1, path);
        preparedStatement.setString(2, date);
        preparedStatement.setString(3, type);
        preparedStatement.execute();
        connection.close();
    }
}
