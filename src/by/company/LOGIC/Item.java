package by.company.LOGIC;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.text.DateFormat;

public class Item {
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty date = new SimpleStringProperty("");
    private SimpleStringProperty type = new SimpleStringProperty("");
    private SimpleStringProperty size = new SimpleStringProperty("");

    private File file;

    Item(File file){
        setFile(file);
        String name = file.getName();
    }

    private void setFile(File file){
        this.file = file;
    }
}
