package by.company.GUI;

/**
 * This class create scene for main window
 * @author Maxim Borodin 650505-1
 * @version 0.0.2
 * @since 27.02.2018
 */

import by.company.DAO.MyInformationDAO;
import by.company.DAO.MyUsersDAO;
import by.company.LOGIC.AddItem;
import by.company.LOGIC.Constants;
import by.company.LOGIC.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


//name, date, type, size
public class MainWindow extends Scene {

    private Button videoButton;
    private Button musicButton;
    private Button documentButton;
    private Button bookButton;
    private TextField searchField;
    private Label usernameLabel;
    private Button addButton;
    private Button remooveButton;
    private TableView<Item> table;
    private TableColumn<Item, String> column_name;
    private TableColumn<Item, String> column_date;
    private TableColumn column_type;
    private TableColumn<Item, String> column_size;
    private ObservableList<Item> video_list = FXCollections.observableArrayList();
    private ObservableList<Item> audio_list = FXCollections.observableArrayList();
    private ObservableList<Item> book_list = FXCollections.observableArrayList();
    private ObservableList<Item> documents_list = FXCollections.observableArrayList();
    private ObservableList<Item> allList = FXCollections.observableArrayList();
    String tableState = new String(Constants.VIDEO);
    private String username;
    private int id;
    int left_size;



    public MainWindow(Pane MainPane, String username, int id) throws SQLException {

        super(MainPane, 610,400);
        this.username = new String(username);
        this.id = id;
        usernameLabel = new Label("Welcome, " + username);
        videoButton = new Button("Video");
        musicButton = new Button("Music");
        documentButton = new Button("Document");
        bookButton = new Button("Book");
        searchField = new TextField();
        addButton = new Button("Add");
        remooveButton = new Button("Remove");
        table = new TableView<Item>();
        column_name = new TableColumn<Item, String>("Name");
        column_date = new TableColumn<Item, String>("Date");
        column_size = new TableColumn<Item, String>("Size");
        column_type = new TableColumn<Item, String>("Type");
        addButton.setDisable(false);
        remooveButton.setDisable(true);

        whatsize();

        switch (username) {
            case "admin":
                remooveButton.setDisable(false);
                break;
            case "Guest":
                addButton.setDisable(true);
                break;
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
                    try {
                        openFile(row.getItem());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
        column_size.setSortable(false);
        column_date.setSortable(false);

        MainPane.getChildren().addAll(usernameLabel, videoButton, musicButton, documentButton,
                    bookButton, searchField, addButton, table, remooveButton);

        table.setLayoutX(120);
        table.setLayoutY(40);
        table.setStyle("-fx-font-size: 15;" +
                "-fx-pref-width: 490;" +
                "-fx-pref-height: 360;");
        column_name.setStyle("-fx-pref-width: 250;");
        column_type.setStyle("-fx-pref-width: 50;");
        column_size.setStyle("-fx-pref-width: 90;");
        column_date.setStyle("-fx-pref-width: 100;");

        videoButton.setLayoutX(0);
        videoButton.setLayoutY(40);
        videoButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        videoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(video_list);
                tableState = new String(Constants.VIDEO);
            }
        });

        musicButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        musicButton.setLayoutX(0);
        musicButton.setLayoutY(100);
        musicButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(audio_list);
                tableState = new String(Constants.AUDIO);
            }
        });

        documentButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        documentButton.setLayoutX(0);
        documentButton.setLayoutY(160);
        documentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(documents_list);
                tableState = new String(Constants.DOCUMENTS);
            }
        });

        bookButton.setStyle("-fx-pref-width: 120;"
                   + "-fx-pref-height: 60");
        bookButton.setLayoutX(0);
        bookButton.setLayoutY(220);
        bookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(book_list);
                tableState = new String(Constants.BOOK);
            }
        });

        addButton.setStyle("-fx-pref-width: 120;" +
                    "-fx-pref-height: 60");
        addButton.setLayoutX(0);
        addButton.setLayoutY(280);
        addButton.setOnAction(new AddAction());

        remooveButton.setLayoutY(340);
        remooveButton.setLayoutX(0);
        remooveButton.setStyle("-fx-pref-width: 120;" +
                "-fx-pref-height: 60;");
        remooveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Item item = table.getSelectionModel().getSelectedItem();
                if(item != null){
                    removeItem(item);
                }
            }
        });

        usernameLabel.setStyle("-fx-pref-height: 40;" +
                    "-fx-pref-width: 305;" +
                    "-fx-font-size: 25;" +
                    "-fx-border-style: solid");

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
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

        searchField.setStyle("-fx-pref-height: 40;" +
                    "-fx-pref-width: 305;" +
                    "-fx-font-size: 18");
        searchField.setLayoutX(305);
        searchField.setLayoutY(0);
        searchField.setPromptText("What you want?");



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

    private void openFile(Item item) throws IOException {


        if(new File(item.getPath()).exists()){
            Desktop.getDesktop().open(new File(item.getPath()));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ooops...");
            alert.setHeaderText("This file does not exist.");
            alert.setContentText("Most likely someone deleted it manually during the program.");
            alert.showAndWait();
        }


    }

    private ObservableList<Item> setListAtSearch(){
        allList.clear();
        allList.addAll(audio_list);
        allList.addAll(book_list);
        allList.addAll(documents_list);
        allList.addAll(video_list);

        return allList;
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
                AddItem addItem = new AddItem(left_size, id);
                addItem.setList(addItem.getFiles());
                whatsize();
                audio_list.addAll(addItem.getAudio_list());
                addItem.clearAudio_list();
                video_list.addAll(addItem.getVideo_list());
                addItem.clearVideo_list();
                book_list.addAll(addItem.getBook_list());
                addItem.clearBook_list();
                documents_list.addAll(addItem.getDocuments_list());
                addItem.clearDocuments_list();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hmm...");
                alert.setHeaderText(null);
                alert.setContentText("Sorry, but this file already exists.");
                alert.showAndWait();
            }
        }
    }

    public void whatsize() {
        if (id != 0 && id != 2) {
            MyUsersDAO myUsersDAO = new MyUsersDAO();
            try {
                left_size = myUsersDAO.check_info_size(id);
                if(left_size <= 1){
                    addButton.setText("Add\n(0 KB)");
                    addButton.setDisable(true);
                }else{
                    String string  = new String(Double.toString(((double)left_size/Constants.KB)));
                    addButton.setText("Add\n(" + string.substring(0, string.indexOf(".")+2) + " KB)");
                    addButton.setAlignment(Pos.CENTER);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}


