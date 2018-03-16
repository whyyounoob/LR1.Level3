package by.company.LOGIC;

import by.company.DAO.MyDAOFactory;
import by.company.DAO.MyInformationDAO;
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
    private static ArrayList<Item> audio_list = new ArrayList<Item>();
    private static ArrayList<Item> video_list = new ArrayList<Item>();
    private static ArrayList<Item> book_list = new ArrayList<Item>();
    private static ArrayList<Item> documents_list = new ArrayList<Item>();
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
                        size_of_users += size_of_items;
                        MyUsersDAO myUsersDAO = new MyUsersDAO();
                        myUsersDAO.setSize(size_of_users, id);
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
                audio_list.add(item);
                break;
            case "BOOK":
                book_list.add(item);
                break;
            case "DOCUMENTS":
                documents_list.add(item);
                break;
            case "VIDEO":
                video_list.add(item);
                break;
        }
    }

    public ArrayList<Item> getAudio_list() {
        return audio_list;
    }

    public void clearAudio_list(){
        audio_list.clear();
    }

    public void clearVideo_list(){
        video_list.clear();
    }

    public void clearBook_list(){
        book_list.clear();
    }

    public void clearDocuments_list(){
        documents_list.clear();
    }

    public ArrayList<Item> getBook_list() {
        return book_list;
    }

    public ArrayList<Item> getDocuments_list() {
        return documents_list;
    }

    public ArrayList<Item> getVideo_list() {
        return video_list;
    }
}
