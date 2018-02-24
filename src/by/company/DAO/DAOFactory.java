package by.company.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DAOFactory {
    public Connection getConnection()throws SQLException;
    public UsersDAO getUsersDAO(Connection connection) throws SQLException;
    public VideoDAO getVideoDAO() throws SQLException;
}
