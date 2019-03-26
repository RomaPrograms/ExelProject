package dbconnection.information_from_db;

import entity.Chair;
import entity.Faculty;
import entity.FacultyConstants;
import entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import reader.TableFileReader;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityDAO extends AbstractDAO {

    private static final String FIND_CHAIRS_IN_DATABASE_BY_YEAR
            = "SELECT ch.chairName, ch.chairUnivName, ch.chairNpp,"
            + " ch.chairSchool, ch.chairMethodic, ch.chairScience, ch.mathtechBase,"
            + " ch.chairIdeolog, ch.chairSecurity, ch.chairRate, ch.chairQual FROM"
            + " chairs ch inner join addedfiles a ON a.id = ch.addedFilesId"
            + " where a.yearOfTable = (?)";

    private static final String FIND_PATH_TO_FILE_IN_DATABASE_BY_YEAR_AND_CHAIR
            = "SELECT pathToFile FROM addedfiles WHERE yearOfTable = (?)"
            + " AND chairName = (?)";

    private static final String FIND_PERSONS_IN_DATABASE_BY_YEAR
            = "SELECT p.pchair, p.prank, p.pname, p.pcategory, p.prate, p.prateQual FROM"
            + " persons p inner join addedfiles a ON a.id = p.addedFiles where"
            + " a.yearOfTable = (?)";

    private static final String ADD_TO_DATABASE_TO_ADDEDFILES
            = "INSERT INTO addedfiles (yearOfTable, chairName, pathToFile)"
            + " values (?, ?, ?)";

    private static final String ADD_TO_DATABASE_TO_PERSONS
            = "INSERT INTO persons (pchair, pyear, prank, pname,"
            + " pcategory, prate, prateQual, addedFiles) values (?, ?, ?, ?, ?, ?, ?,"
            + "(SELECT (id) from addedfiles where yearOfTable = (?)"
            + " and chairName = (?)))";

    private static final String ADD_TO_DATABASE_TO_CHAIRS
            = "INSERT INTO chairs (chairUnivName, chairYear, chairName,"
            + " chairNpp, chairSchool, chairMethodic, chairScience,"
            + " mathtechBase, chairIdeolog, chairSecurity, chairRate, chairQual,"
            + " addedFilesID) values (?,"
            + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT "
            + "(id) from addedfiles where"
            + " yearOfTable = (?) and chairName = (?)))";

    private static final String FIND_PATH_TO_FILE_IN_DATABASE_BY_YEAR
            = "SELECT pathToFile FROM addedfiles WHERE yearOfTable = (?)";

    private static final String DELETE_DATA_FROM_CONSTANTS
            = "TRUNCATE TABLE constants";

    private static final String ADD_DATA_TO_CONSTANTS
            = "INSERT INTO constants (constStudy, constMethodical, constIdeology,"
            + " constScience, constMatBase, constVSandOBVS, constCMP) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_CONSTANTS
            = "SELECT * FROM constants";

    private static final String MAX_YEAR_OF_TABLE
            = "SELECT MAX(yearOfTable) FROM addedfiles";

    private static final String MIN_YEAR_OF_TABLE
            = "select MIN(yearOfTable) FROM addedfiles;";

    private static final String FIND_INFORMATION_ABOUT_FACULTIES
            = "select chairUnivName, chairYear, avg(chairSchool), avg(chairMethodic),"
            + " avg(chairScience), avg(mathtechBase) from chairs group by"
            + " chairUnivName,chairYear";

    public EntityDAO() {
        super();
    }

    @Override
    public ObservableList<Chair> findChairsByYear(String year) {
        ObservableList<Chair> list = FXCollections.observableArrayList();
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement(FIND_CHAIRS_IN_DATABASE_BY_YEAR);
            statement.setString(1, year);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(new Chair(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getDouble(4),
                        resultSet.getDouble(5),
                        resultSet.getDouble(6),
                        resultSet.getDouble(7),
                        resultSet.getDouble(8),
                        resultSet.getDouble(9),
                        resultSet.getDouble(10),
                        resultSet.getDouble(11)));
            }

            closeResultSet(resultSet);
            return list;

        } catch (NullPointerException ex) { //calls when resultSet is empty.
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(statement);
        }
        return list;
    }

    @Override
    public ObservableList<Person> findPersonsByYear(String year) {
        ObservableList<Person> list = FXCollections.observableArrayList();
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement(FIND_PERSONS_IN_DATABASE_BY_YEAR);
            statement.setString(1, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Person(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)));
            }
            closeResultSet(resultSet);
            return list;
        } catch (NullPointerException ex) { //calls when resultSet is empty.
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(statement);
        }
        return list;
    }

    @Override
    public String findPathToFileByChairAndYear(String year, String chair) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    FIND_PATH_TO_FILE_IN_DATABASE_BY_YEAR_AND_CHAIR);
            statement.setString(1, year);
            statement.setString(2, chair);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString(1);
            }

            closeResultSet(resultSet);

        } catch (NullPointerException ex) { //calls when resultSet is empty.
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(statement);
        }

        return new String();
    }

    @Override
    public boolean isExistInDatabase(String year, String chair) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    FIND_PATH_TO_FILE_IN_DATABASE_BY_YEAR_AND_CHAIR);
            statement.setString(1, year);
            statement.setString(2, chair);
            ResultSet resultSet = statement.executeQuery();
            boolean isExist = resultSet.next();
            closeResultSet(resultSet);
            return isExist;

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(statement);
        }
        return false;
    }

    @Override
    public void addDataToDatabase(int year, String name, String pathToFile) {
        try {
            TableFileReader tableFileReader =
                    new TableFileReader(new File(pathToFile));

            addDataToAddedFilesDatabase(year, name, pathToFile);
            addDataToPersonsDatabase(year, name, tableFileReader);
            addDataToChairDatabase(year, name, tableFileReader);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addDataToPersonsDatabase(
            int year, String name,
            TableFileReader tableFileReader) throws SQLException {

        PreparedStatement preparedStatement =
                connection.prepareStatement(ADD_TO_DATABASE_TO_PERSONS);

        for (int i = 1; i <= tableFileReader.GetPersonCountFromFile(); i++) {
            preparedStatement.setString(1,
                    tableFileReader.GetChairFromFile());
            preparedStatement.setString(2,
                    String.valueOf(year));
            preparedStatement.setString(3,
                    tableFileReader.GetRankFromFile(i + 4));
            preparedStatement.setString(4,
                    tableFileReader.GetNameFromFile(i + 4));
            preparedStatement.setString(5,
                    tableFileReader.GetCategoryFromFile(i + 4));
            preparedStatement.setDouble(6,
                    tableFileReader.GetRatingFromFile(i + 4));
            preparedStatement.setDouble(7,
                    tableFileReader.GetQualRateFromFile(i + 4));
            preparedStatement.setInt(8, year);
            preparedStatement.setString(9, name);

            preparedStatement.executeUpdate();
        }

        closePreparedStatement(preparedStatement);
    }

    private void addDataToChairDatabase(
            int year, String name,
            TableFileReader tableFileReader) throws SQLException {

        PreparedStatement preparedStatement =
                connection.prepareStatement(ADD_TO_DATABASE_TO_CHAIRS);
        preparedStatement.setString(1,
                tableFileReader.GetUnivName());
        preparedStatement.setInt(2, year);
        preparedStatement.setString(3,
                tableFileReader.GetChName());
        preparedStatement.setDouble(4,
                tableFileReader.GetChNpp());
        preparedStatement.setDouble(5,
                tableFileReader.GetChStudyWork());
        preparedStatement.setDouble(6,
                tableFileReader.GetChMethodicalWork());
        preparedStatement.setDouble(7,
                tableFileReader.GetChSinceWork());
        preparedStatement.setDouble(8,
                tableFileReader.GetChMatBase());
        preparedStatement.setDouble(9,
                tableFileReader.GetChIdWork());
        preparedStatement.setDouble(10,
                tableFileReader.GetChSecurity());
        preparedStatement.setDouble(11,
                tableFileReader.GetChRate());
//        preparedStatement.setDouble(12,
//                        34.67);
        preparedStatement.setDouble(12,
                tableFileReader.GetChQualRate());
        preparedStatement.setInt(13, year);
        preparedStatement.setString(14, name);
        preparedStatement.executeUpdate();
        closePreparedStatement(preparedStatement);
    }

    private void addDataToAddedFilesDatabase(
            int year, String name, String pathToFile) throws SQLException {

        PreparedStatement preparedStatement =
                connection.prepareStatement(ADD_TO_DATABASE_TO_ADDEDFILES);
        preparedStatement.setInt(1, year);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, pathToFile);
        preparedStatement.executeUpdate();

        closePreparedStatement(preparedStatement);
    }

    public void addConstantToDatabase(double constStudy, double constMethodical,
                                      double constIdeology, int constScience,
                                      int matBase, double constVSandOBVS,
                                      double CMP) {
        PreparedStatement preparedStatement = null;
        try {
            deleteDataFromConstants();
            preparedStatement
                    = connection.prepareStatement(ADD_DATA_TO_CONSTANTS);
            preparedStatement.setDouble(1, constStudy);
            preparedStatement.setDouble(2, constMethodical);
            preparedStatement.setDouble(3, constIdeology);
            preparedStatement.setDouble(4, constScience);
            preparedStatement.setDouble(5, matBase);
            preparedStatement.setDouble(6, constVSandOBVS);
            preparedStatement.setDouble(7, CMP);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public FacultyConstants getFacultyConstants() {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement
                    = connection.prepareStatement(SELECT_CONSTANTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return new FacultyConstants(resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return null;
    }

    private void deleteDataFromConstants() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement(DELETE_DATA_FROM_CONSTANTS);
        preparedStatement.executeUpdate();
        closePreparedStatement(preparedStatement);
    }

    @Override
    public ObservableList<Faculty> getInformationAboutFaculties() {
        ObservableList<Faculty> faculties = FXCollections.observableArrayList();

            PreparedStatement statement = null;
            FacultyConstants facultyConstants = getFacultyConstants();
            try {
                statement = connection.prepareStatement(
                        FIND_INFORMATION_ABOUT_FACULTIES);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Faculty faculty = new Faculty();

                    faculty.setfName(resultSet.getString(1));
                    faculty.setfYear(resultSet.getInt(2));
                    faculty.setfSchoolWork(Double
                            .parseDouble(resultSet.getString(3)));
                    faculty.setfMethodicalWork(Double
                            .parseDouble(resultSet.getString(4)));
                    faculty.setfSinceWork(Double
                            .parseDouble(resultSet.getString(5)));
                    faculty.setfMatBase(Double
                            .parseDouble(resultSet.getString(6)));
                    if (facultyConstants != null) {
                       faculty.setfConstantMatBase(facultyConstants
                               .getfConstantMatBase());
                       faculty.setfConstantMethodicalWork(facultyConstants
                               .getfConstantMethodicalWork());
                       faculty.setfConstantSinceWork(facultyConstants
                               .getfConstantSinceWork());
                       faculty.setfConstantIdWork(facultyConstants
                               .getfConstantIdWork());
                       faculty.setfConstantSchoolWork(facultyConstants
                               .getfConstantSchoolWork());
                       faculty.setfConstantSMR(facultyConstants
                               .getfConstantSMR());
                       faculty.setfConstantVSandVPVO(facultyConstants
                               .getfConstantVSandVPVO());
                    }
                    faculties.add(faculty);
                }
                closeResultSet(resultSet);
                return faculties;
            } catch (NullPointerException ex) { //calls when resultSet is empty.
                ex.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closePreparedStatement(statement);
            }

        return faculties;
    }

    @Override
    public ObservableList<String> findPathsToFilesByYear(String year) {

        ObservableList<String> observableList
                = FXCollections.observableArrayList();
        PreparedStatement statement = null;
        try {
            statement = connection
                    .prepareStatement(FIND_PATH_TO_FILE_IN_DATABASE_BY_YEAR);
            statement.setString(1, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                observableList.add(resultSet.getString(1));
            }
            closeResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(statement);
        }
        return observableList;
    }

    @Override
    public int getMaxYearOfTables() {
        return getYearOfTable(MAX_YEAR_OF_TABLE);
    }

    @Override
    public int getMinYearOfTables() {
        return getYearOfTable(MIN_YEAR_OF_TABLE);
    }

    private int getYearOfTable(String string) {
        int year = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(string);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                year = resultSet.getInt(1);
            }
            closeResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return year;
    }
}
