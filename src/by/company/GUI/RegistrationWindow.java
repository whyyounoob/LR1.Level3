package by.company.GUI;

/**
 * This class create GUIForm for RegistrationWindow
 * @author Maxim Borodin 650505-1
 * @version 0.0.3
 * @since 21.02.2018
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RegistrationWindow extends Scene {

    private Label RegLabel;
    private Label userlabel;
    private Label passwordlabel;
    private Label emaillabel;
    private Label repeaatpasswordlabel;
    private TextField userfield;
    private TextField emailfield;
    private PasswordField passwordfield;
    private PasswordField repeatpasswordfield;
    private Button signupbtn;

    public RegistrationWindow(Pane pane) {

        super(pane,300,500);
        RegLabel = new Label("Registration");
        userlabel = new Label("Username: ");
        passwordlabel = new Label("Password: ");
        emaillabel = new Label("E-mail: ");
        repeaatpasswordlabel = new Label("Repeat password: ");
        userfield = new TextField("Enter your username: ");
        emailfield = new TextField("Enter your e-mail: ");
        passwordfield = new PasswordField();
        repeatpasswordfield = new PasswordField();
        signupbtn = new Button("SIGN UP");

        pane.getChildren().addAll(RegLabel, userlabel, passwordlabel, emaillabel, repeaatpasswordlabel,
                userfield, emailfield, passwordfield, repeatpasswordfield, signupbtn);

        RegLabel.setStyle("-fx-font-size: 35");
        RegLabel.setLayoutX(50);
        RegLabel.setLayoutY(15);

        userlabel.setStyle("-fx-font-size: 20");
        userlabel.setLayoutY(80);
        userlabel.setLayoutX(50);

        userfield.setLayoutX(50);
        userfield.setLayoutY(110);
        userfield.setStyle("-fx-pref-height: 30;" +
                "-fx-pref-width: 200;" +
                "-fx-font-size: 14");

        passwordlabel.setStyle("-fx-font-size: 20");
        passwordlabel.setLayoutX(50);
        passwordlabel.setLayoutY(160);

        passwordfield.setStyle("-fx-pref-height: 30;" +
                "-fx-pref-width: 200;" +
                "-fx-font-size: 14");
        passwordfield.setLayoutY(190);
        passwordfield.setLayoutX(50);

        repeaatpasswordlabel.setStyle("-fx-font-size: 20");
        repeaatpasswordlabel.setLayoutX(50);
        repeaatpasswordlabel.setLayoutY(240);

        repeatpasswordfield.setStyle("-fx-pref-height: 30;" +
                "-fx-pref-width: 200;" +
                "-fx-font-size: 14");
        repeatpasswordfield.setLayoutY(270);
        repeatpasswordfield.setLayoutX(50);

        emaillabel.setStyle("-fx-font-size: 20");
        emaillabel.setLayoutX(50);
        emaillabel.setLayoutY(320);

        emailfield.setStyle("-fx-pref-height: 30;" +
                "-fx-pref-width: 200;" +
                "-fx-font-size: 14");
        emailfield.setLayoutY(350);
        emailfield.setLayoutX(50);

        signupbtn.setStyle("-fx-pref-height: 25;" +
                "-fx-pref-width: 200;" +
                "-fx-font-size: 18");
        signupbtn.setLayoutX(50);
        signupbtn.setLayoutY(400);
        signupbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

}
