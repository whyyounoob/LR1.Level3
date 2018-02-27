package by.company.DAO;

/**
 * This class contains some constants stringQuerry for DB
 * @author Maxim Borodin 650505-1
 * @version 0.0.1
 * @since 27.02.2018
 */

public class SQLConstants {
    public static final String CHECK_USERNAME = new String("SELECT * FROM `users` where `users`.`username` = ?;");
    public static final String CHECK_EMAIL = new String("SELECT * FROM `users` where `users`.`email` = ?;");
    public static final String INSERT_USER = new String("INSERT INTO users(username, email, password) " +
            "VALUES (?,?,?);");
}