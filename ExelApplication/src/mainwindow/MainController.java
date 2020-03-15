package mainwindow;

import chairwindow.ChairController;
import java.sql.Connection;
import dbconnection.DbConnection;
import dbconnection.information_from_db.EntityDAO;
import exception.FileException;
import facultywindow.FacultyController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ppswindow.PPSController;
import reader.FileManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button idDataAboutPPS;
    private FileManager fileManager = new FileManager();
    private Connection connection;
    private int minYear;
    private int maxYear;
    private static EntityDAO entityDAO = new EntityDAO();

    {
        minYear = entityDAO.getMinYearOfTables();
        maxYear = entityDAO.getMaxYearOfTables();
        PPSController.setYearCheckBoxList(minYear, maxYear);
        ChairController.setYearCheckBoxList(minYear, maxYear);
        FacultyController.setYearCheckBoxList(minYear, maxYear);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = DbConnection.getConnection();
    }

    public void addTable() {
        try {
            FileChooser fileChooser = new FileChooser();
            FileException fileException = new FileException();
            Button okButton = (Button) fileException.alert.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setDisable(true);

            FileChooser.ExtensionFilter fileExtensions =
                    new FileChooser.ExtensionFilter(
                            "Extension filters: ",
                            "*.xls", "*.xlsx", "*.xlsm", "*.xlsb");

            fileChooser.getExtensionFilters().add(fileExtensions);
            List<File> files =
                    fileChooser.showOpenMultipleDialog(null);

            if (files != null) {
                fileException.alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                    }
                });
                fileException.alert.setOnHiding(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                    }
                });
                fileException.alert.setOnShowing(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                    }
                });
                fileException.alert.setOnShown(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                    }
                });

                fileException.callAlert("Идёт загрузка, пожалуйста подождите" +
                        " и НЕ НАЖИМАЙТЕ НА КНОПКИ!");
                for (int i = 0; i < files.size(); i++) {
                    String addingResult = fileManager.AddFile(files.get(i));

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
                                entityDAO.deleteDataFromDatabase(observableList);
                            }
                            minYear = entityDAO.getMinYearOfTables();
                            maxYear = entityDAO.getMaxYearOfTables();
                            PPSController.setYearCheckBoxList(minYear, maxYear);
                            ChairController.setYearCheckBoxList(minYear, maxYear);
                            FacultyController.setYearCheckBoxList(minYear, maxYear);
                        }
                    }
                }
                fileException.closeAlert();
            }
        } catch (IllegalArgumentException | NullPointerException ex) {
            FileException fileException = new FileException();
            fileException.callAlert(ex.getMessage());
        }
    }

    public void showAllTables() {
        openNewWindow("/tablewindow/tables.fxml", 700, 400,
                false);
    }

    public void openWindowDataAboutPPS() {
        openNewWindow("/ppswindow/pps.fxml", 953, 523,
                true);
    }

    public void openWindowDataAboutChair() {
        openNewWindow("/chairwindow/chair.fxml", 1274, 600,
                true);
    }

    public void openWindowDataAboutFaculty() {
        openNewWindow("/facultywindow/faculty.fxml", 960, 600,
                true);
    }

    public void openNewWindow(String path, int width, int height,
                              boolean closeOldWindow) {
        try {

            Stage stage = (Stage) this.idDataAboutPPS.getScene().getWindow();
            if (closeOldWindow) {
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
