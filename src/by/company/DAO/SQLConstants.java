package by.company.DAO;

/**
 * This class contains some constants stringQuerry for DB.
 *
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

public class SQLConstants {

    public static final String CHECK_USERNAME = new String("SELECT * FROM `users` " +
            "where `users`.`username` = ?;");
    public static final String CHECK_EMAIL = new String("SELECT * FROM `users` where `users`.`email` = ?;");
    public static final String INSERT_USER = new String("INSERT INTO users(username, email, password, " +
            "size_items, last_date) VALUES (?,?,?,?,?);");

    public static final String INSERT_INFO = new String("INSERT INTO Information(path_info, date_info, " +
            "type_info) VALUES (?,?,?);");
    public static final String SELECT_INFO = new String("SELECT path_info, date_info FROM Information WHERE type_info = ?;");
    public static final String DELETE_INFO = new String("DELETE FROM Information WHERE path_info = ?;");
    public static final String CHECK_SIZE_ITEMS = new String("SELECT size_items, last_date FROM users " +
            "WHERE id = ?;");
    public static final String UPADATE_DATE_SIZE = new String("UPDATE users SET last_date = ?, " +
            "size_items = ? WHERE id = ?;");
    public static final String GET_PASSWORD = new String("SELECT password, id FROM users WHERE username = ?;");
}
