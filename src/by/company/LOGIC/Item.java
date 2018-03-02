package by.company.LOGIC;

import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Item {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty date = new SimpleStringProperty("");
    private SimpleStringProperty extension = new SimpleStringProperty("");
    private SimpleStringProperty size = new SimpleStringProperty("");
    private SimpleStringProperty type = new SimpleStringProperty("");
    private String path = new String("");

    private File file;

    Item(File file){
        setFile(file);
        setName();
        setPath();
        setExtension();
        setType();
        setDate();
        setSize();
    }

    private void setFile(File file){
        this.file = file;
    }

    private void setName(){
        this.name.set(file.getName());
        System.out.println(this.name);
    }

    private void setPath(){
        this.path = new String(file.getAbsolutePath());
        System.out.println(file.getAbsolutePath());
    }

    private void setExtension(){
        this.extension.set(path.substring(path.lastIndexOf(".")));
        System.out.println(this.extension);
    }

    private void setType(){
        switch(this.extension.getValue()){
            case ".docx":
            case ".doc":
            case ".ppt":
            case ".pptx":
            case ".xlsx":
                type.set("DOCUMENTS");
                break;
            case ".pdf":
                type.set("BOOK");
                break;
            case ".mp3":
            case ".wav":
                type.set("AUDIO");
                break;
            case ".mp4":
            case ".mkv":
            case ".avi":
                type.set("VIDEO");
                break;
        }
        System.out.println(type);
    }

    private void setDate(){
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.date.set(LocalDateTime.now().format(dateTime));
        System.out.println(date);
    }

    private void setSize(){
        long size = file.length();
        String string;
        if(size<=Constants.KB){
            this.size.set("1 KB");
        }else if(size<=Constants.MB){
            string = Double.toString(((double)size/Constants.KB));
            this.size.set((string.substring(0, string.indexOf(".")+2)) + " KB");
        }else if(size<=Constants.GB){
            string = Double.toString(((double)size/Constants.MB));
            this.size.set((string.substring(0, string.indexOf(".")+2)) + " MB");
        }else{
            string = Double.toString(((double)size/Constants.GB));
            this.size.set((string.substring(0, string.indexOf(".")+2)) + " GB");
        }
        System.out.println(this.size.toString());
    }

    private void addToDB(){
            
    }

    public String getType(){
        return type.getValue();
    }

}
