package by.company.DAO;

/**
 * This class works with table "users" in DB
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.company.DAO.SQLConstants;
import javafx.scene.control.Alert;

public class MyUsersDAO implements UsersDAO {

    private String username;
    private String email;
    private String password;
    private String repeat_password;

    public MyUsersDAO(String un, String em, String pw, String rpw){
        username = new String(un);
        email = new String(em);
        password = new String(pw);
        repeat_password = new String(rpw);

    }
    @Override
    public void createUser() throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ooops");
        alert.setHeaderText(null);

        PreparedStatement preparedStatementUsername = connection.prepareStatement(SQLConstants.CHECK_USERNAME);
        preparedStatementUsername.setString(1,username);
        PreparedStatement preparedStatementEmail = connection.prepareStatement(SQLConstants.CHECK_EMAIL);
        preparedStatementEmail.setString(1,email);
        if(preparedStatementUsername.executeQuery().next()){
            alert.setContentText("Sorry, but this username is already taken.");
            alert.showAndWait();
            preparedStatementUsername.close();
        } else if(preparedStatementEmail.executeQuery().next()){
            alert.setContentText("Sorry, but this e-mail is already taken.");
            alert.showAndWait();
            preparedStatementEmail.close();
        } else if(password.equals(repeat_password)){
            PreparedStatement ps = connection.prepareStatement(SQLConstants.INSERT_USER);
            ps.setString(1, username);
            ps.setString(2,email);
            ps.setString(3, password);
            ps.execute();
            ps.close();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.show();

        }else{
            alert.setContentText("Sorry, but your passwords do not match.");
            alert.showAndWait();

        }
        connection.close();
    }

    @Override
    public void loginUser() throws SQLException{

    }
}
