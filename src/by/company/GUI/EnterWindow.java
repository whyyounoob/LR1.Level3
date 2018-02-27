package by.company.GUI;

/**
 * This class create GUIForm for EnterWinodow
 * @author Maxim Borodin 650505-1
 * @version 0.0.4
 * @since 27.02.2018
 */

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
import javafx.stage.Stage;

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

        pane = new Pane();
        username_label = new Label("Username: ");
        password_label = new Label("Password: ");
        username_field = new TextField();
        password_field = new PasswordField();
        HomeCatalog = new Label ("Home Catalog");
        loginbtn = new Button("Log In");
        sign_upbtn = new Button("Sign Up");
        guestbtn = new Button("Log In as Guest");

        pane.getChildren().addAll(HomeCatalog, username_label, password_label, username_field, password_field,
                loginbtn, sign_upbtn, guestbtn);
        pane.setStyle("-fx-background-color: aliceblue");

        HomeCatalog.setFont(new Font("Times New Roman",24));
        HomeCatalog.setLayoutX(120);
        HomeCatalog.setLayoutY(20);

        username_label.setFont(new Font("Times New Roman", 19));
        username_label.setLayoutX(40);
        username_label.setLayoutY(70);

        password_label.setFont(new Font("Times New Roman", 19));
        password_label.setLayoutX(45);
        password_label.setLayoutY(110);

        username_field.setPrefSize(200,30);
        username_field.setLayoutX(130);
        username_field.setLayoutY(65);
        username_field.setPromptText("Enter your username...");

        password_field.setPrefSize(200,30);
        password_field.setLayoutX(130);
        password_field.setLayoutY(105);
        password_field.setPromptText("Enter your password...");

        loginbtn.setPrefSize(140,30);
        loginbtn.setLayoutX(40);
        loginbtn.setLayoutY(140);

        sign_upbtn.setPrefSize(140,30);
        sign_upbtn.setLayoutX(190);
        sign_upbtn.setLayoutY(140);
        sign_upbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EnterStage.setScene(new RegistrationWindow(new Pane()));

            }
        });

        guestbtn.setPrefSize(290,30);
        guestbtn.setLayoutX(40);
        guestbtn.setLayoutY(180);
        guestbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EnterStage.setScene(new MainWindow(new Pane(), "Guest"));
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
