package by.company.DAO;

/**
 * This class works with table "users" in DB
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import by.company.LOGIC.Constants;
import by.company.LOGIC.Encryption;
import by.company.LOGIC.RegEx;
import javafx.scene.control.Alert;

public class MyUsersDAO implements UsersDAO {

    private String username;
    private String email;
    private String password;
    private String repeat_password;
    private int offset;

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
    public void createUser() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
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
            String hashPass = Encryption.getHashedPassword(password);
            PreparedStatement ps = connection.prepareStatement(SQLConstants.INSERT_USER);
            ps.setString(1, username);
            ps.setString(2,email);
            ps.setString(3, hashPass);
            ps.setInt(4,1);
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ooops...");
        alert.setHeaderText(null);
        Connection connection = new MyDAOFactory().getConnection();
        PreparedStatement ps = connection.prepareStatement(SQLConstants.GET_PASSWORD);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            try {
                if(Encryption.getHashedPassword(password).equals(rs.getString(1))){
                    int ret = rs.getInt(2);
                    return ret;
                }else {

                    alert.setContentText("Wrong password.");
                    alert.showAndWait();
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }else{
            alert.setContentText("Wrong username.");
            alert.showAndWait();
        }
       rs.close();
       ps.close();
        connection.close();
        return 0;
    }
    @Override
    public int checkInfoSize(int id) throws SQLException {
        int size = Constants.MB_10;
        Connection connection = new MyDAOFactory().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.CHECK_SIZE_ITEMS);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            if(LocalDateTime.now().format(dateTime).equals(resultSet.getString(2))){
                size -= resultSet.getInt(1);
            } else {
                PreparedStatement statement = connection.prepareStatement(SQLConstants.UPADATE_DATE_SIZE);
                statement.setString(1, LocalDateTime.now().format(dateTime));
                statement.setInt(2, 0);
                statement.setInt(3,id);
                statement.execute();
                statement.close();
            }
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return size;
    }

    @Override
    public void setSize(int size, int id) throws SQLException {
        Connection connection = new MyDAOFactory().getConnection();
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.UPADATE_DATE_SIZE);
        preparedStatement.setString(1, LocalDateTime.now().format(dateTime));
        preparedStatement.setInt(2, size);
        preparedStatement.setInt(3,id);
        preparedStatement.execute();
        connection.close();
    }
}
