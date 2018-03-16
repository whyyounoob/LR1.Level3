package by.company.GUI;

/**
 * This class create GUIForm for EnterWinodow
 * @author Maxim Borodin 650505-1
 * @version 0.0.4
 * @since 27.02.2018
 */

import by.company.DAO.MyUsersDAO;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;

public class EnterWindow extends Application {

    Pane pane;
    Label username_label;
    Label password_label;
    TextField username_field;
    PasswordField password_field;
    Label HomeCatalog;
    Button loginbtn;
    Button sign_upbtn;
    Button guestbtn;

    /**
     * In this method we are create window.
     */

    @Override
    public void start(Stage EnterStage){
        EnterStage.setResizable(false);

        pane = new Pane();
        username_label = new Label(GUIConstants.USERNAME_LABEL_TEXT);
        password_label = new Label(GUIConstants.PASSWORD_LABEL_TEXT);
        username_field = new TextField();
        password_field = new PasswordField();
        HomeCatalog = new Label (GUIConstants.HOMECATALOG_LABEL);
        loginbtn = new Button(GUIConstants.LOGINBTN_TEXT);
        sign_upbtn = new Button(GUIConstants.SIGNUPBTN_TEXT);
        guestbtn = new Button(GUIConstants.GUESTBTN_TEXT);

        pane.getChildren().addAll(HomeCatalog, username_label, password_label, username_field, password_field,
                loginbtn, sign_upbtn, guestbtn);
        pane.setStyle("-fx-background-color: aliceblue");

        HomeCatalog.setStyle("-fx-font-family: sans-serif;\n" +
                "     -fx-font-size: 28;\n" +
                "     -fx-fill: linear-gradient(to top, cyan, dodgerblue);\n" +
                "     -fx-effect: dropshadow(gaussian, dodgerblue, 15, 0.25, 5, 5);");
        HomeCatalog.setLayoutX(100);
        HomeCatalog.setLayoutY(20);

        username_label.setFont(new Font(GUIConstants.FONT_TEXT, GUIConstants.SIZE_19));
        username_label.setLayoutX(40);
        username_label.setLayoutY(70);

        password_label.setFont(new Font(GUIConstants.FONT_TEXT, GUIConstants.SIZE_19));
        password_label.setLayoutX(45);
        password_label.setLayoutY(110);

        username_field.setPrefSize(GUIConstants.SIZE_200,GUIConstants.SIZE_30);
        username_field.setLayoutX(130);
        username_field.setLayoutY(65);
        username_field.setPromptText("Enter your username...");


        password_field.setPrefSize(GUIConstants.SIZE_200,GUIConstants.SIZE_30);
        password_field.setLayoutX(130);
        password_field.setLayoutY(105);
        password_field.setPromptText("Enter your password...");

        loginbtn.setPrefSize(140,30);
        loginbtn.setLayoutX(40);
        loginbtn.setLayoutY(140);
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyUsersDAO mud = new MyUsersDAO(username_field.getText(), password_field.getText());
                try {
                    int id = mud.loginUser();
                    if(id!=0){
                        EnterStage.setScene(new MainWindow(new Pane(), username_field.getText(), id));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        sign_upbtn.setPrefSize(140,30);
        sign_upbtn.setLayoutX(190);
        sign_upbtn.setLayoutY(140);
        sign_upbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new RegistrationWindow(new Pane()));
                stage.showAndWait();
            }
        });

        guestbtn.setPrefSize(290,30);
        guestbtn.setLayoutX(40);
        guestbtn.setLayoutY(180);
        guestbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    EnterStage.setScene(new MainWindow(new Pane(), "Guest", 0));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Scene EnterScene = new Scene(pane, 380,250);
        EnterStage.setScene(EnterScene);
        EnterStage.show();
    }

    /**
     * This method launch our window.
     */

    public void showingEW(){
        launch();
    }
}
