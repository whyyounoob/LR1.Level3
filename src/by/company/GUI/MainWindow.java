package by.company.GUI;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This class create scene for main window.
 *
 * @author Maxim Borodin 650505-1
 * @version 0.0.2
 * @since 27.02.2018
 */

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
    private TableColumn<Item, String> columnName;
    private TableColumn<Item, String> columnDate;
    private TableColumn columnType;
    private TableColumn<Item, String> columnSize;
    private ObservableList<Item> videoList = FXCollections.observableArrayList();
    private ObservableList<Item> audioList = FXCollections.observableArrayList();
    private ObservableList<Item> bookList = FXCollections.observableArrayList();
    private ObservableList<Item> documentList = FXCollections.observableArrayList();
    private ObservableList<Item> allList = FXCollections.observableArrayList();
    private String tableState = new String(Constants.VIDEO);
    private String username;
    private int id;
    private int leftSize;



    public MainWindow(final Pane mainPane, final String username, final int id) throws SQLException {

        super(mainPane, 610, 400);
        this.username = new String(username);
        this.id = id;
        usernameLabel = new Label("Welcome, " + this.username);
        videoButton = new Button("Video");
        musicButton = new Button("Music");
        documentButton = new Button("Document");
        bookButton = new Button("Book");
        searchField = new TextField();
        addButton = new Button("Add");
        remooveButton = new Button("Remove");
        table = new TableView<Item>();
        columnName = new TableColumn<Item, String>("Name");
        columnDate = new TableColumn<Item, String>("Date");
        columnSize = new TableColumn<Item, String>("Size");
        columnType = new TableColumn<Item, String>("Type");
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

        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("extension"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnSize.setCellValueFactory(new PropertyValueFactory<>("size"));

        table.getColumns().addAll(columnName, columnType, columnDate, columnSize);
        setvideoList();
        setaudioList();
        setbookList();
        setdocumentList();
        table.setItems(videoList);
        table.setRowFactory(tv -> {
            TableRow<Item> row = new TableRow<Item>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        openFile(row.getItem());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
        columnSize.setSortable(false);
        columnDate.setSortable(false);

        mainPane.getChildren().addAll(usernameLabel, videoButton, musicButton, documentButton,
                bookButton, searchField, addButton, table, remooveButton);

        table.setLayoutX(120);
        table.setLayoutY(40);
        table.setStyle("-fx-font-size: 15;" +
                "-fx-pref-width: 490;" +
                "-fx-pref-height: 360;");
        columnName.setStyle("-fx-pref-width: 250;");
        columnType.setStyle("-fx-pref-width: 50;");
        columnSize.setStyle("-fx-pref-width: 90;");
        columnDate.setStyle("-fx-pref-width: 100;");

        videoButton.setLayoutX(0);
        videoButton.setLayoutY(40);
        videoButton.setStyle("-fx-pref-width: 120;" +
                "-fx-pref-height: 60");
        videoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                table.setItems(videoList);
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
                table.setItems(audioList);
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
                table.setItems(documentList);
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
                table.setItems(bookList);
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
                if (item != null) {
                    removeItem(item);
                }
            }
        });

        usernameLabel.setStyle("-fx-pref-height: 40;" +
                "-fx-pref-width: 305;" +
                "-fx-font-size: 25;" +
                "-fx-border-style: solid");

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Item> lst = new FilteredList<>(setListAtSearch(), p -> true);
            lst.setPredicate(item -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (item.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
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

    public void setvideoList() throws SQLException {
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        AddItem addItem = new AddItem(myInformationDAO.getInfo(Constants.VIDEO), Constants.VIDEO);
        videoList.addAll(addItem.getvideoList());
        addItem.clearvideoList();
    }

    public void setaudioList() throws SQLException {
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        AddItem addItem = new AddItem(myInformationDAO.getInfo(Constants.AUDIO), Constants.AUDIO);
        audioList.addAll(addItem.getaudioList());
        addItem.clearaudioList();
    }

    public void setbookList() throws SQLException {
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        AddItem addItem = new AddItem(myInformationDAO.getInfo(Constants.BOOK), Constants.BOOK);
        bookList.addAll(addItem.getbookList());
        addItem.clearbookList();
    }

    public void setdocumentList() throws SQLException {
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        AddItem addItem = new AddItem(myInformationDAO.getInfo(Constants.DOCUMENTS), Constants.DOCUMENTS);
        documentList.addAll(addItem.getdocumentList());
        addItem.cleardocumentList();
    }

    private void openFile(Item item) throws IOException {


        if (new File(item.getPath()).exists()) {
            Desktop.getDesktop().open(new File(item.getPath()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ooops...");
            alert.setHeaderText("This file does not exist.");
            alert.setContentText("Most likely someone deleted it manually during the program.");
            alert.showAndWait();
        }


    }

    private ObservableList<Item> setListAtSearch() {
        allList.clear();
        allList.addAll(audioList);
        allList.addAll(bookList);
        allList.addAll(documentList);
        allList.addAll(videoList);

        return allList;
    }

    private void removeItem(Item item) {
        switch (tableState) {
            case "AUDIO":
                audioList.remove(item);
                break;
            case "BOOK":
                bookList.remove(item);
                break;
            case "DOCUMENTS":
                documentList.remove(item);
                break;
            case "VIDEO":
                videoList.remove(item);
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
                AddItem addItem = new AddItem(leftSize, id);
                addItem.setList(addItem.getFiles());
                whatsize();
                audioList.addAll(addItem.getaudioList());
                addItem.clearaudioList();
                videoList.addAll(addItem.getvideoList());
                addItem.clearvideoList();
                bookList.addAll(addItem.getbookList());
                addItem.clearbookList();
                documentList.addAll(addItem.getdocumentList());
                addItem.cleardocumentList();
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
                leftSize = myUsersDAO.checkInfoSize(id);
                if (leftSize <= 1) {
                    addButton.setText("Add\n(0 KB)");
                    addButton.setDisable(true);
                } else {
                    String string = new String(Double.toString(((double) leftSize / Constants.KB)));
                    addButton.setText("Add\n(" + string.substring(0, string.indexOf(".") + 2) + " KB)");
                    addButton.setAlignment(Pos.CENTER);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}


