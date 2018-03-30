package by.company.GUI;


import by.company.DAO.MyUsersDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.SQLException;

/**
 * This class create GUIForm for EnterWinodow.
 *
 * @author Maxim Borodin 650505-1
 * @version 0.0.4
 * @since 27.02.2018
 */

public class EnterWindow extends Application {

    private Pane pane;
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label homeCatalog;
    private Button loginbtn;
    private Button signUpBtn;
    private Button guestbtn;

    /**
     * In this method we are create window.
     */

    @Override
    public void start(final Stage enterStage) {
        enterStage.setResizable(false);

        pane = new Pane();
        usernameLabel = new Label(GUIConstants.USERNAMELABELTEXT);
        passwordLabel = new Label(GUIConstants.passwordLabel_TEXT);
        usernameField = new TextField();
        passwordField = new PasswordField();
        homeCatalog = new Label(GUIConstants.homeCatalog_LABEL);
        loginbtn = new Button(GUIConstants.LOGINBTN_TEXT);
        signUpBtn = new Button(GUIConstants.SIGNUPBTN_TEXT);
        guestbtn = new Button(GUIConstants.GUESTBTN_TEXT);

        pane.getChildren().addAll(homeCatalog, usernameLabel, passwordLabel, usernameField, passwordField,
                loginbtn, signUpBtn, guestbtn);
        pane.setStyle("-fx-background-color: aliceblue");

        homeCatalog.setStyle("-fx-font-family: sans-serif;\n" +
                "     -fx-font-size: 28;\n" +
                "     -fx-fill: linear-gradient(to top, cyan, dodgerblue);\n" +
                "     -fx-effect: dropshadow(gaussian, dodgerblue, 15, 0.25, 5, 5);");
        homeCatalog.setLayoutX(100);
        homeCatalog.setLayoutY(GUIConstants.SIZE_20);

        usernameLabel.setFont(new Font(GUIConstants.FONT_TEXT, GUIConstants.SIZE_19));
        usernameLabel.setLayoutX(40);
        usernameLabel.setLayoutY(70);

        passwordLabel.setFont(new Font(GUIConstants.FONT_TEXT, GUIConstants.SIZE_19));
        passwordLabel.setLayoutX(45);
        passwordLabel.setLayoutY(110);

        usernameField.setPrefSize(GUIConstants.SIZE_200, GUIConstants.SIZE_30);
        usernameField.setLayoutX(130);
        usernameField.setLayoutY(65);
        usernameField.setPromptText("Enter your username...");


        passwordField.setPrefSize(GUIConstants.SIZE_200, GUIConstants.SIZE_30);
        passwordField.setLayoutX(130);
        passwordField.setLayoutY(105);
        passwordField.setPromptText("Enter your password...");

        loginbtn.setPrefSize(140, 30);
        loginbtn.setLayoutX(40);
        loginbtn.setLayoutY(140);
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                MyUsersDAO mud = new MyUsersDAO(usernameField.getText(), passwordField.getText());
                try {
                    int id = mud.loginUser();
                    if (id != 0) {
                        enterStage.setScene(new MainWindow(new Pane(), usernameField.getText(), id));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        signUpBtn.setPrefSize(140, 30);
        signUpBtn.setLayoutX(190);
        signUpBtn.setLayoutY(140);
        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new RegistrationWindow(new Pane()));
                stage.showAndWait();
            }
        });

        guestbtn.setPrefSize(290, 30);
        guestbtn.setLayoutX(40);
        guestbtn.setLayoutY(180);
        guestbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                try {
                    enterStage.setScene(new MainWindow(new Pane(), "Guest", 0));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Scene enterScene = new Scene(pane, 380, 250);
        enterStage.setScene(enterScene);
        enterStage.show();
    }

    /**
     * This method launch our window.
     */

    public void showingEW() {
        launch();
    }
}
