package by.company.LOGIC;

import by.company.GUI.EnterWindow;
import by.company.GUI.MainWindow;
import by.company.GUI.RegistrationWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartClass{
    public static void main(String[] args){
        EnterWindow ew = new EnterWindow();
        ew.showingEW();
    }
}
