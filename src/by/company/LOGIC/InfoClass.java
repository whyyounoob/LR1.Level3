package by.company.LOGIC;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * This class contain info about some file
 * @author Maxim Baradzin
 * @version 0.0.1
 * @since 24.02.2018
 */
public class InfoClass{
    private String NameFile;
    private String PathFile;
    private Double SizeFile;
/**
 * Constructor with all parametrs
 */

    public InfoClass(final String name, final String Path, double Size){
        NameFile = name;
        PathFile = Path;
        SizeFile = Size;
    }
    /**
     *This method gives to us access for NameFile
    */
    public String getNameFile (){
        return NameFile;
    }
    /**
     *This method gives to us access for PathFile
     */
    public String getPathFile(){
        return PathFile;
    }
    /**
     *This method gives to us access for SizeFile
     */
    public double getSizeFile(){
        return SizeFile;
    }
}
