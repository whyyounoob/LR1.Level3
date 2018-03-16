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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import by.company.DAO.SQLConstants;
import by.company.LOGIC.Constants;
import by.company.LOGIC.RegEx;
import javafx.scene.control.Alert;
import javafx.scene.control.ResizeFeaturesBase;

public class MyUsersDAO implements UsersDAO {

    private String username;
    private String email;
    private String password;
    private String repeat_password;

    public MyUsersDAO(final String un, final String em, final String pw, final String rpw){
        username = new String(un);
        email = new String(em);
        password = new String(pw);
        repeat_password = new String(rpw);

    }

    public MyUsersDAO(final String un, final String pw){
        username = new String(un);
        password = new String(pw);
    }
    public MyUsersDAO(){}

    @Override
    public void createUser() throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ooops");
        alert.setHeaderText(null);
        RegEx reg = new RegEx();
        PreparedStatement preparedStatementUsername = connection.prepareStatement(SQLConstants.CHECK_USERNAME);
        preparedStatementUsername.setString(1,username);
        PreparedStatement preparedStatementEmail = connection.prepareStatement(SQLConstants.CHECK_EMAIL);
        preparedStatementEmail.setString(1,email);
        if(!reg.checkEmail(email)){
            alert.setContentText("Incorrect email");
            alert.showAndWait();
        } else if(preparedStatementUsername.executeQuery().next()){
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
            ps.setInt(4,0);
            DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            ps.setString(5, LocalDateTime.now().format(dateTime));
            ps.execute();
            ps.close();
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Yspeh!!!");
            alert.show();

        }else{
            alert.setContentText("Sorry, but your passwords do not match.");
            alert.showAndWait();

        }
        connection.close();
    }

    @Override
    public int loginUser() throws SQLException{
        Connection connection = new MyDAOFactory().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.LOGIN_USER);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int ret = resultSet.getInt(1);
            preparedStatement.close();
            connection.close();
           return ret;
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ooops...");
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong. Verify that the data entered is correct.");
            alert.showAndWait();
        }
        connection.close();
        return 0;
    }
    @Override
    public int check_info_size(int id) throws SQLException {
        int size = Constants.MB_10;
        Connection connection = new MyDAOFactory().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.CHECK_SIZE_ITEMS);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            if(LocalDateTime.now().format(dateTime).equals(resultSet.getString(2))){
                size -= resultSet.getInt(1);
            }
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return size;
    }

}
