package by.company.LOGIC;

import by.company.DAO.MyUsersDAO;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddItem {
    private FileChooser fileChooser;
    private static ArrayList<Item> audioList = new ArrayList<Item>();
    private static ArrayList<Item> videoList = new ArrayList<Item>();
    private static ArrayList<Item> bookList = new ArrayList<Item>();
    private static ArrayList<Item> documentList = new ArrayList<Item>();
    private static int id;
    private int size_of_items;
    private int size_of_users;

    public AddItem(int size, int id) throws SQLException {
        this.id = id;
        size_of_users = size;
        size_of_items = 0;
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Documents",
                Constants.DOCUMENTS_EXTENSIONS), new FileChooser.ExtensionFilter("Book",
                Constants.BOOK_EXTENSIONS), new FileChooser.ExtensionFilter("Audio",
                Constants.AUDIO_EXTENSIONS), new FileChooser.ExtensionFilter("Video",
                Constants.VIDEO_EXTENSIONS), new FileChooser.ExtensionFilter("All",
                Constants.ALL_EXTENSIONS));
    }

    public AddItem(List<Item> items, String type){
        for(int i = 0; i<items.size(); i++){
            setType(items.get(i), type);
        }
    }

    public List<File> getFiles(){
        List<File> list = fileChooser.showOpenMultipleDialog(new Stage());
        return list;
    }

    public int setList(final List<File> list) throws SQLException {
        if(list != null) {
            for(int i = 0; i < list.size(); i++){
                size_of_items += list.get(i).length();
            }
            if(id != 2 && size_of_items>size_of_users){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Oh no...");
                alert.setHeaderText(null);
                alert.setContentText("File size is too large.");
                alert.showAndWait();
            } else{
                for (int i = 0; i < list.size(); i++) {
                    Item item = new Item(list.get(i));
                    setType(item, item.getType());
                    if(id != 2){
                        size_of_users -= size_of_items;
                        MyUsersDAO myUsersDAO = new MyUsersDAO();
                        myUsersDAO.setSize(Constants.MB_10 - size_of_users, id);
                    }
                }
            }
            //if(!usertype.equals("admin"))
        }
        return size_of_users;
    }

    public void setType(final Item item, final String type){
        switch(type){
            case "AUDIO":
                audioList.add(item);
                break;
            case "BOOK":
                bookList.add(item);
                break;
            case "DOCUMENTS":
                documentList.add(item);
                break;
            case "VIDEO":
                videoList.add(item);
                break;
        }
    }

    public ArrayList<Item> getaudioList() {
        return audioList;
    }

    public void clearaudioList(){
        audioList.clear();
    }

    public void clearvideoList(){
        videoList.clear();
    }

    public void clearbookList(){
        bookList.clear();
    }

    public void cleardocumentList(){
        documentList.clear();
    }

    public ArrayList<Item> getbookList() {
        return bookList;
    }

    public ArrayList<Item> getdocumentList() {
        return documentList;
    }

    public ArrayList<Item> getvideoList() {
        return videoList;
    }
}
