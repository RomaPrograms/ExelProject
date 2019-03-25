package dbconnection.information_from_db;

import java.sql.Connection;
import dbconnection.DbConnection;
import entity.Chair;
import entity.FacultyConstants;
import entity.Person;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {
    protected Connection connection;
    public AbstractDAO() {
        this.connection = DbConnection.getConnection();
    }
    public abstract ObservableList<Chair> findChairsByYear(String year);
    public abstract ObservableList<Person> findPersonsByYear(String year);
    public abstract String findPathToFileByChairAndYear(String year,
                                                        String chair);
    public abstract ObservableList<String> findPathsToFilesByYear(String year);
    public abstract void addDataToDatabase(int year, String name,
                                           String pathToFile);
    public abstract void addConstantToDatabase(int constStudy,
                                               int constMethodical,
                                               int constScience, int matBase);
    public abstract FacultyConstants getFacultyConstants();
    public abstract int getMaxYearOfTables();
    public abstract int getMinYearOfTables();
    public abstract boolean isExistInDatabase(String year, String chair);
    public void closePreparedStatement(PreparedStatement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeResultSet(ResultSet st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
