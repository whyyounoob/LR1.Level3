package by.company.DAO;

import java.sql.SQLException;

public interface InformationDAO {
    public void setVideo(String path, String date, String type) throws SQLException;
}
