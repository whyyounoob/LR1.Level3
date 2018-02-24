package by.company.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class MyUsersDAO implements UsersDAO {
    @Override
    public void createUser() throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();
        
    }
}
