package by.company.GUI;

/**
 * This class create scene for main window
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

import by.company.LOGIC.InfoClass;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class MainWindow extends Scene {

    Button VideoButton;
    Button MusicButton;
    Button DocumentButton;
    Button BookButton;
    TextField search_field;
    Button search_btn;
    Label username_lbl;
    Button add_btn;
    TableView <InfoClass> table;
    TableColumn<InfoClass, String> name_fileCol;
    TableColumn<InfoClass, String> path_fileCol;
    TableColumn<InfoClass, Double> size_fileCol;


    public MainWindow(Pane MainPane, String username) {
        super(MainPane, 610,340);

        table = new TableView<InfoClass>();
        name_fileCol = new TableColumn<InfoClass, String>("Name File");
        path_fileCol = new TableColumn<InfoClass, String>("Patn File");
        size_fileCol = new TableColumn<InfoClass, Double>("Size File");

        username_lbl = new Label("Welcome, " + username);
        VideoButton = new Button("Video");
        MusicButton = new Button("Music");
        DocumentButton = new Button("Document");
        BookButton = new Button("Book");
        search_field = new TextField();
        search_btn = new Button("Search");
        add_btn = new Button("Add");

        MainPane.getChildren().addAll(username_lbl, VideoButton, MusicButton, DocumentButton,
                    BookButton, search_field, search_btn, add_btn, table);

        name_fileCol.setPrefWidth(170);
        path_fileCol.setPrefWidth(260);
        size_fileCol.setPrefWidth(60);
        table.getColumns().addAll(name_fileCol, path_fileCol,size_fileCol);
        table.setLayoutX(120);
        table.setLayoutY(40);
        table.setStyle("-fx-pref-width: 490;" +
                "-fx-pref-height: 300;");

        VideoButton.setLayoutX(0);
        VideoButton.setLayoutY(40);
        VideoButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");

        MusicButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        MusicButton.setLayoutX(0);
        MusicButton.setLayoutY(100);

        DocumentButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        DocumentButton.setLayoutX(0);
        DocumentButton.setLayoutY(160);

        BookButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        BookButton.setLayoutX(0);
        BookButton.setLayoutY(220);

        add_btn.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        add_btn.setLayoutX(0);
        add_btn.setLayoutY(280);

        username_lbl.setStyle("-fx-pref-height: 40;" +
                    "-fx-pref-width: 280;" +
                    "-fx-font-size: 25;" +
                    "-fx-border-style: solid");

        search_field.setStyle("-fx-pref-height: 40;" +
                    "-fx-pref-width: 250;" +
                    "-fx-font-size: 18");
        search_field.setLayoutX(280);
        search_field.setLayoutY(0);
        search_field.setPromptText("What you want?");

        search_btn.setLayoutX(530);
        search_btn.setLayoutY(0);
        search_btn.setStyle("-fx-pref-height: 40;" +
                    "-fx-pref-width: 80");
    }
}
