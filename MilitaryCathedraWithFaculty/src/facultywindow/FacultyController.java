package facultywindow;

import dbconnection.information_from_db.EntityDAO;
import entity.Faculty;
import entity.FacultyConstants;
import entity.Person;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FacultyController implements Initializable {
    @FXML
    public TableView tableView;
    @FXML
    public TableColumn firstColumn;
    @FXML
    public TableColumn secondColumn;
    @FXML
    public TableColumn thirdColumn;
    @FXML
    public TableColumn forthColumn;
    @FXML
    public TableColumn fifthColumn;
    @FXML
    public TableColumn sixthColumn;
    @FXML
    public TableColumn seventhColumn;
    @FXML
    public TableColumn eighthColumn;
    @FXML
    public TableColumn ninthColumn;
    @FXML
    public TableColumn tenthColumn;
    @FXML
    public TableColumn eleventhColumn;
    @FXML
    public TableColumn twelfthColumn;
    @FXML
    public TableColumn thirteenthColumn;
    @FXML
    public Button backButton;
    @FXML
    public Button additionalInformationButton;

    private ObservableList<Faculty> faculties
            = FXCollections.observableArrayList();

    private static FXMLLoader fxmlLoader = new FXMLLoader();
    private static Parent loader = null;
    private static EntityDAO entityDAO = new EntityDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) { //когда будем получать инфу с базы данных, мы вставим в каждый
        //из факультетов инфу с константами.
        faculties.add(new Faculty("1", 1, 1, 1, 1, 1, 1, 1, 1));
        faculties.add(new Faculty("2", 2, 2, 2, 2, 2, 2, 2, 2));
        faculties.add(new Faculty("3", 3, 3, 3, 3, 3, 3, 3, 3));
        faculties.add(new Faculty("4", 4, 4, 4, 4, 4, 4, 4, 4));

        this.firstColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, String>("fName"));
        this.secondColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fStudyWork"));
        this.thirdColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantStudyWork"));
        this.forthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fMethodicalWork"));
        this.fifthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantMethodicalWork"));
        this.sixthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fIdWork"));
        this.seventhColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fSinceWork"));
        this.eighthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantSinceWork"));
        this.ninthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fMatBase"));
        this.tenthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantMatBase"));
        this.eleventhColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fVSANDVPVO"));
        this.twelfthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fSMR"));
        this.thirteenthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fRate"));

        tableView.setItems(faculties);
    }

    @FXML
    public void changeConstants(ActionEvent event) {
        Parent loader = null;
        try {
            loader = new FXMLLoader().load(getClass()
                    .getResource("/facultywindow/constantswindow/const.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Информация о кафедрах");
            newStage.setScene(new Scene(loader, 333, 357));
            newStage.setResizable(true);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exit() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.hide();

        fxmlLoader.setLocation(getClass().getResource("/mainwindow/sample.fxml"));
        try {
            loader = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage newStage = new Stage();
        newStage.setTitle("Проект.");
        newStage.setScene(new Scene(loader));
        newStage.show();
    }
}
