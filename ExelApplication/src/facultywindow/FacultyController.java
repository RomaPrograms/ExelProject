package facultywindow;

import dbconnection.information_from_db.EntityDAO;
import entity.Faculty;
import entity.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;

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
    @FXML
    public ComboBox yearComboBox;

    private ObservableList<Faculty> faculties
            = FXCollections.observableArrayList();

    private ObservableList<String> yearCheckBoxList
            = FXCollections.observableArrayList("2019", "2018", "2017",
            "2016", "2015");

    private static FXMLLoader fxmlLoader = new FXMLLoader();
    private static Parent loader = null;
    private static EntityDAO entityDAO = new EntityDAO();
    private static String staticYear = "выбрать год";
    private FilteredList<Faculty> filter;

    @Override
    public void initialize(URL location, ResourceBundle resources) { //когда будем получать инфу с базы данных, мы вставим в каждый
        //из факультетов инфу с константами.
        this.yearComboBox.setItems(yearCheckBoxList);
        this.yearComboBox.setValue(staticYear);
        faculties = entityDAO.getInformationAboutFaculties();

        this.firstColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, String>("fName"));
        this.secondColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fSchoolWork"));
        this.thirdColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantSchoolWork"));
        this.forthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fMethodicalWork"));
        this.fifthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantMethodicalWork"));
        this.sixthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantIdWork"));
        this.seventhColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fSinceWork"));
        this.eighthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantSinceWork"));
        this.ninthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fMatBase"));
        this.tenthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantMatBase"));
        this.eleventhColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantVSandVPVO"));
        this.twelfthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fConstantSMR"));
        this.thirteenthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fRate"));

    }

    @FXML
    public void changeConstants(ActionEvent event) {
        Parent loader = null;
        try {
            loader = new FXMLLoader().load(getClass()
                    .getResource("/facultywindow/constantswindow/const.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Информация о кафедрах");
            newStage.setScene(new Scene(loader, 333, 494));
            newStage.setResizable(true);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void yearComboBoxChanged(ActionEvent event) {
        staticYear = String.valueOf(yearComboBox.getValue());
        filter = new FilteredList(faculties, e -> true);

        filter.setPredicate((Predicate<? super Faculty>) (Faculty faculty) -> {
            return String.valueOf(faculty.getfYear()).equals(staticYear);
        });

        SortedList<Faculty> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
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
