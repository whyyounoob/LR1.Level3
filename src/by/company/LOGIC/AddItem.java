package by.company.LOGIC;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddItem {
    private FileChooser fileChooser;
    private ArrayList<Item> audio_list = new ArrayList<Item>();
    private ArrayList<Item> video_list = new ArrayList<Item>();
    private ArrayList<Item> book_list = new ArrayList<Item>();
    private ArrayList<Item> documents_list = new ArrayList<Item>();

    public AddItem(){
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Documents",
                Constants.DOCUMENTS_EXTENSIONS), new FileChooser.ExtensionFilter("Book",
                Constants.BOOK_EXTENSIONS), new FileChooser.ExtensionFilter("Audio",
                Constants.AUDIO_EXTENSIONS), new FileChooser.ExtensionFilter("Video",
                Constants.VIDEO_EXTENSIONS), new FileChooser.ExtensionFilter("All",
                Constants.ALL_EXTENSIONS));

    }

    public List<File> getFiles(){
        List<File> list = fileChooser.showOpenMultipleDialog(new Stage());
        return list;
    }

    public void setList(List<File> list){
        for(int i = 0;i<list.size();i++){
            new Item(list.get(i));
        }
    }
}
