package by.company.LOGIC;

import by.company.DAO.MyInformationDAO;
import javafx.beans.property.SimpleStringProperty;

import java.awt.event.ContainerAdapter;
import java.io.File;
import java.sql.SQLException;
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

    Item(File file) throws SQLException {
        setFile(file);
        setName();
        setPath();
        setExtension();
        setType();
        setDate();
        setSize();
        //addToDB();
    }

    Item(){

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
        this.extension.set(path.substring(path.lastIndexOf(".")+1));
        System.out.println(this.extension);
    }

    private void setType(){
        switch(this.extension.getValue()){
            case "docx":
            case "doc":
            case "ppt":
            case "pptx":
            case "xlsx":
                type.set(Constants.DOCUMENTS);
                break;
            case "pdf":
                type.set(Constants.BOOK);
                break;
            case "mp3":
            case "wav":
                type.set(Constants.AUDIO);
                break;
            case "mp4":
            case "mkv":
            case "avi":
                type.set(Constants.VIDEO);
                break;
        }
        System.out.println(type.getValue());
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

    private void addToDB() throws SQLException {
        MyInformationDAO myInformationDAO = new MyInformationDAO();
        myInformationDAO.setInfo(path, date.getValue(), type.getValue());
    }

    public String getType(){
        return type.getValue();
    }

    public String getName(){ return name.getValue();}

    public String getExtension(){ return extension.getValue(); }

    public String getDate() { return date.getValue(); }

    public String getSize(){ return size.getValue();}
}

