package mainwindow;

import com.mysql.jdbc.Connection;
import dbconnection.DbConnection;
import dbconnection.information_from_db.EntityDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import reader.FileManager;
import tablewindow.TableController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public MenuItem menuItemDownloaded;
    @FXML
    public MenuItem menuItemDownload;
    @FXML
    public Button idDataAboutChair;
    @FXML
    private Button idDataAboutPPS;

    public TableController tableController;

    public FileManager fileManager = new FileManager();
    public Connection connection;
    public int minYear;
    public int maxYear;
    private static EntityDAO entityDAO = new EntityDAO();
    //public static PPSController ppsController = new PPSController();
    //public static FacultyController chairController = new FacultyController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = (Connection) DbConnection.getConnection();
        tableController = new TableController();

        //isOldTable();
        //ppsController.setYearCheckBoxList(minYear, maxYear);
        //chairController.setYearCheckBoxList(minYear, maxYear);
    }

    public void addTable() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File(
                    "C:\\Users\\user\\Desktop"));

            FileChooser.ExtensionFilter fileExtensions =
                    new FileChooser.ExtensionFilter(
                            "Extension filters: ",
                            "*.xls", "*.xlsx", "*.xlsm", "*.xlsb");

            fileChooser.getExtensionFilters().add(fileExtensions);
            List<File> files =
                    fileChooser.showOpenMultipleDialog(null);

            if (files != null) {
                for (var file : files) {
                    String addingResult = fileManager.AddFile(file);

                    System.out.println(addingResult);
                    int lastIndex = addingResult.lastIndexOf('.');
                    int lastIndex1 = addingResult.lastIndexOf('\\');
                    String newAddingResult =
                            addingResult.substring(0, lastIndex1);
                    int lastIndex2 = newAddingResult.lastIndexOf('\\');

                    int year = Integer.parseInt(
                            newAddingResult.substring(lastIndex2 + 1));
                    String chair =
                            addingResult.substring(lastIndex1 + 1, lastIndex);

                    if (!entityDAO.isExistInDatabase(String.valueOf(year),
                            chair)) {
                        if (addingResult != null) {
                            entityDAO.addDataToDatabase(year, chair,
                                    addingResult);
                            if (entityDAO.getMaxYearOfTables() - entityDAO.getMinYearOfTables() > 4) {
                                ObservableList<String> observableList
                                        = entityDAO
                                        .findPathsToFilesByYear(String.valueOf(year - 5));
                                tableController.deleteTableFromDatabase(observableList);
                                //ppsController.setYearCheckBoxList(minYear, maxYear);
                                //chairController.setYearCheckBoxList(minYear, maxYear);
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void showAllTables() {
        openNewWindow("/tablewindow/tables.fxml", 500, 300,
                false);
    }

    public void openWindowDataAboutPPS() {
        openNewWindow("/ppswindow/pps.fxml", 933, 523,
                true);
    }

    public void openWindowDataAboutChair() {
        openNewWindow("/chairwindow/chair.fxml", 1144, 600,
                true);
    }
    
    public void openWindowDataAboutFaculty(){
        openNewWindow("/facultywindow/faculty.fxml", 960, 600,
                true);
    }

    public void openNewWindow(String path, int width, int height,
                              boolean closeOldWindow) {
        try {

            Stage stage = (Stage) this.idDataAboutPPS.getScene().getWindow();
            if(closeOldWindow) {
                stage.close();
            }
            Parent loader = new FXMLLoader().load(getClass()
                    .getResource(path));
            Stage newStage = new Stage();
            newStage.setTitle("Информация о кафедрах");
            newStage.setScene(new Scene(loader, width, height));
            newStage.setResizable(true);
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeWindow() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Platform.exit();
    }
}
