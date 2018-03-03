package by.company.GUI;

/**
 * This class create scene for main window
 * @author Maxim Borodin 650505-1
 * @version 0.0.2
 * @since 27.02.2018
 */

import by.company.LOGIC.AddItem;
import by.company.LOGIC.InfoClass;
import by.company.LOGIC.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

//name, date, type, size
public class MainWindow extends Scene {

    Button VideoButton;
    Button MusicButton;
    Button DocumentButton;
    Button BookButton;
    TextField search_field;
    Button search_btn;
    Label username_lbl;
    Button add_btn;
    TableView<Item> table;
    TableColumn<Item, String> column_name;
    TableColumn<Item, String> column_date;
    TableColumn column_type;
    TableColumn<Item, String> column_size;
    ObservableList<Item> video_list;


    public MainWindow(Pane MainPane, String username) {
        super(MainPane, 610,340);


        username_lbl = new Label("Welcome, " + username);
        VideoButton = new Button("Video");
        MusicButton = new Button("Music");
        DocumentButton = new Button("Document");
        BookButton = new Button("Book");
        search_field = new TextField();
        search_btn = new Button("Search");
        add_btn = new Button("Add");
        table = new TableView<Item>();
        column_name = new TableColumn<Item, String>("Name");
        column_date = new TableColumn<Item, String>("Date");
        column_size = new TableColumn<Item, String>("Size");
        column_type = new TableColumn<Item, String>("Type");

        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_type.setCellValueFactory(new PropertyValueFactory<>("extension"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        column_size.setCellValueFactory(new PropertyValueFactory<>("size"));

        table.getColumns().addAll(column_name,column_type, column_date, column_size);


        MainPane.getChildren().addAll(username_lbl, VideoButton, MusicButton, DocumentButton,
                    BookButton, search_field, search_btn, add_btn, table);

        table.setLayoutX(120);
        table.setLayoutY(40);
        table.setStyle("-fx-font-size: 15;" +
                "-fx-pref-width: 490;" +
                "-fx-pref-height: 300;");
        column_name.setStyle("-fx-pref-width: 210;");
        column_type.setStyle("-fx-pref-width: 90;");
        column_size.setStyle("-fx-pref-width: 90;");
        column_date.setStyle("-fx-pref-width: 100;");

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
        add_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    AddItem addItem = new AddItem();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

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
