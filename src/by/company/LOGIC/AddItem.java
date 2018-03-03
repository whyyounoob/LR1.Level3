package by.company.LOGIC;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddItem {
    private FileChooser fileChooser;
    private ArrayList<Item> audio_list = new ArrayList<Item>();
    private ArrayList<Item> video_list = new ArrayList<Item>();
    private ArrayList<Item> book_list = new ArrayList<Item>();
    private ArrayList<Item> documents_list = new ArrayList<Item>();

    public AddItem() throws SQLException {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Documents",
                Constants.DOCUMENTS_EXTENSIONS), new FileChooser.ExtensionFilter("Book",
                Constants.BOOK_EXTENSIONS), new FileChooser.ExtensionFilter("Audio",
                Constants.AUDIO_EXTENSIONS), new FileChooser.ExtensionFilter("Video",
                Constants.VIDEO_EXTENSIONS), new FileChooser.ExtensionFilter("All",
                Constants.ALL_EXTENSIONS));
        setList(getFiles());
    }
 
    public AddItem(final String path, final String date, final String type){

    }

    public List<File> getFiles(){
        List<File> list = fileChooser.showOpenMultipleDialog(new Stage());
        return list;
    }

    public void setList(final List<File> list) throws SQLException {
        for(int i = 0; i<list.size(); i++){
            Item item = new Item(list.get(i));
            switch(item.getType()){
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
    }

    public ArrayList<Item> getAudio_list() {
        return audio_list;
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
