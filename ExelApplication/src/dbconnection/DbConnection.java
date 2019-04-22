package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String PASSWORD = "9512684Roma";
    private static final String USER = "root";
    private static final String URL =
            "jdbc:sqlite:D:\\инфа\\Военная Кафедра\\MilitaryProgram\\ExelProject\\ExelApplication\\militarydatabase.sqlite";
    private final static String DRIVER = "org.sqlite.JDBC";
    private static final String SQCONN = "jdbc:sqlite:militarydatabase.sqlite";
    private static Connection INSTANCE;

    private DbConnection() {
    }

    public static Connection getConnection() {
        try {
            if (INSTANCE == null) {
                Class.forName(DRIVER);
                INSTANCE = DriverManager.getConnection(SQCONN);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return INSTANCE;
    }
}
