package by.company.GUI;

/**
 * This class create scene for main window
 * @author Maxim Borodin 650505-1
 * @version 0.0.2
 * @since 27.02.2018
 */

import by.company.DAO.MyInformationDAO;
import by.company.LOGIC.AddItem;
import by.company.LOGIC.Constants;
import by.company.LOGIC.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//name, date, type, size
public class MainWindow extends Scene {

    private Button VideoButton;
    private Button MusicButton;
    private Button DocumentButton;
    private Button BookButton;
    private TextField search_field;
    private Label username_lbl;
    private Button add_btn;
    private Button remove_btn;
    private TableView<Item> table;
    private TableColumn<Item, String> column_name;
    private TableColumn<Item, String> column_date;
    private TableColumn column_type;
    private TableColumn<Item, String> column_size;
    private ObservableList<Item> video_list = FXCollections.observableArrayList();
    private ObservableList<Item> audio_list = FXCollections.observableArrayList();
    private ObservableList<Item> book_list = FXCollections.observableArrayList();
    private ObservableList<Item> documents_list = FXCollections.observableArrayList();
    String tableState = new String(Constants.VIDEO);
    private String username;



    public MainWindow(Pane MainPane, String username) throws SQLException {

        super(MainPane, 610,400);
        this.username = new String(username);
        username_lbl = new Label("Welcome, " + username);
        VideoButton = new Button("Video");
        MusicButton = new Button("Music");
        DocumentButton = new Button("Document");
        BookButton = new Button("Book");
        search_field = new TextField();
        add_btn = new Button("Add");
        remove_btn = new Button("Remove");
        table = new TableView<Item>();
        column_name = new TableColumn<Item, String>("Name");
        column_date = new TableColumn<Item, String>("Date");
        column_size = new TableColumn<Item, String>("Size");
        column_type = new TableColumn<Item, String>("Type");
        add_btn.setDisable(false);
        remove_btn.setDisable(true);

        switch(username){
            case "admin":
                remove_btn.setDisable(false);
                break;
            case "Guest":
                add_btn.setDisable(true);
                break;
            default:
                if(true){}
        }

        column_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        column_type.setCellValueFactory(new PropertyValueFactory<>("extension"));
        column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        column_size.setCellValueFactory(new PropertyValueFactory<>("size"));

        table.getColumns().addAll(column_name,column_type, column_date, column_size);
        setVideo_list();
        setAudio_list();
        setBook_list();
        setDocuments_list();
        table.setItems(video_list);
        table.setRowFactory(tv ->{
            TableRow<Item> row = new TableRow<Item>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    openFile(row.getItem());
                }
            });
            return row;
        });


        MainPane.getChildren().addAll(username_lbl, VideoButton, MusicButton, DocumentButton,
                    BookButton, search_field, add_btn, table, remove_btn);

        table.setLayoutX(120);
        table.setLayoutY(40);
        table.setStyle("-fx-font-size: 15;" +
                "-fx-pref-width: 490;" +
                "-fx-pref-height: 360;");
        column_name.setStyle("-fx-pref-width: 250;");
        column_type.setStyle("-fx-pref-width: 50;");
        column_size.setStyle("-fx-pref-width: 90;");
        column_date.setStyle("-fx-pref-width: 100;");

        VideoButton.setLayoutX(0);
        VideoButton.setLayoutY(40);
        VideoButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        VideoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(video_list);
                tableState = new String(Constants.VIDEO);
            }
        });

        MusicButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        MusicButton.setLayoutX(0);
        MusicButton.setLayoutY(100);
        MusicButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(audio_list);
                tableState = new String(Constants.AUDIO);
            }
        });

        DocumentButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        DocumentButton.setLayoutX(0);
        DocumentButton.setLayoutY(160);
        DocumentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(documents_list);
                tableState = new String(Constants.DOCUMENTS);
            }
        });

        BookButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        BookButton.setLayoutX(0);
        BookButton.setLayoutY(220);
        BookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(book_list);
                tableState = new String(Constants.BOOK);
            }
        });

        add_btn.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        add_btn.setLayoutX(0);
        add_btn.setLayoutY(280);
        add_btn.setOnAction(new AddAction());

        remove_btn.setLayoutY(340);
        remove_btn.setLayoutX(0);
        remove_btn.setStyle("-fx-pref-width: 120;" +
                "-fx-pref-height: 60;");
        remove_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Item item = table.getSelectionModel().getSelectedItem();
                if(item != null){
                    removeItem(item);
                }
            }
        });

        username_lbl.setStyle("-fx-pref-height: 40;" +
                    "-fx-pref-width: 280;" +
                    "-fx-font-size: 25;" +
                    "-fx-border-style: solid");

        search_field.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Item> lst = new FilteredList<>(setListAtSearch(),p -> true);
            lst.setPredicate(item -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(item.getName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else { return false;}
            });
            table.setItems(lst);
        });

        search_field.setStyle("-fx-pref-height: 40;" +
                    "-fx-pref-width: 250;" +
                    "-fx-font-size: 18");
        search_field.setLayoutX(280);
        search_field.setLayoutY(0);
        search_field.setPromptText("What you want?");


    }

    public void setVideo_list() throws SQLException {
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        AddItem addItem = new AddItem(myInformationDAO.getInfo(Constants.VIDEO), Constants.VIDEO);
        video_list.addAll(addItem.getVideo_list());
        addItem.clearVideo_list();
    }

    public void setAudio_list()throws  SQLException{
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        AddItem addItem = new AddItem(myInformationDAO.getInfo(Constants.AUDIO), Constants.AUDIO);
        audio_list.addAll(addItem.getAudio_list());
        addItem.clearAudio_list();
    }

    public void setBook_list() throws  SQLException{
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        AddItem addItem = new AddItem(myInformationDAO.getInfo(Constants.BOOK), Constants.BOOK);
        book_list.addAll(addItem.getBook_list());
        addItem.clearBook_list();
    }

    public void setDocuments_list() throws  SQLException{
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        AddItem addItem = new AddItem(myInformationDAO.getInfo(Constants.DOCUMENTS), Constants.DOCUMENTS);
        documents_list.addAll(addItem.getDocuments_list());
        addItem.clearDocuments_list();
    }

    private void openFile(Item item){
        try {
            Desktop.getDesktop().open(new File(item.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Item> setListAtSearch(){
        switch (tableState){
            case "AUDIO":
               return audio_list;
            case "BOOK":
                return book_list;
            case "DOCUMENTS":
                return documents_list;
            case "VIDEO":
                return video_list;
        }
        return null;
    }

    private void removeItem(Item item){
        switch (tableState){
            case "AUDIO":
                audio_list.remove(item);
                break;
            case "BOOK":
                book_list.remove(item);
                break;
            case "DOCUMENTS":
                documents_list.remove(item);
                break;
            case "VIDEO":
                video_list.remove(item);
                break;
        }
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        try {
            myInformationDAO.removeInfo(item.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    class AddAction implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try {
                AddItem addItem = new AddItem();

                audio_list.addAll(addItem.getAudio_list());
                addItem.clearAudio_list();
                video_list.addAll(addItem.getVideo_list());
                addItem.clearVideo_list();
                book_list.addAll(addItem.getBook_list());
                addItem.clearBook_list();
                documents_list.addAll(addItem.getDocuments_list());
                addItem.clearDocuments_list();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hmm...");
                alert.setHeaderText(null);
                alert.setContentText("Sorry, but this file already exists.");
                alert.showAndWait();

            }
        }
    }
}


