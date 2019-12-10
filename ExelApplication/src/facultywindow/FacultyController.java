package facultywindow;

import dbconnection.information_from_db.EntityDAO;
import entity.Faculty;
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
import java.util.function.Predicate;

public class FacultyController implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn firstColumn;
    @FXML
    private TableColumn secondColumn;
    @FXML
    private TableColumn thirdColumn;
    @FXML
    private TableColumn forthColumn;
    @FXML
    private TableColumn fifthColumn;
    @FXML
    private TableColumn sixthColumn;
    @FXML
    private TableColumn seventhColumn;
    @FXML
    private TableColumn eighthColumn;
    @FXML
    private TableColumn ninthColumn;
    @FXML
    private TableColumn tenthColumn;
    @FXML
    private TableColumn eleventhColumn;
    @FXML
    private TableColumn twelfthColumn;
    @FXML
    private TableColumn thirteenthColumn;
    @FXML
    private Button backButton;
    @FXML
    private Button additionalInformationButton;
    @FXML
    private ComboBox yearComboBox;

    public static ObservableList<Faculty> faculties
            = FXCollections.observableArrayList();

    private static ObservableList<String> yearCheckBoxList;

    private Parent loader;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private static EntityDAO entityDAO = new EntityDAO();
    private static String staticYear = "выбрать год";
    private static FilteredList<Faculty> filter;

    public static void setYearCheckBoxList(int minYear, int maxYear) {
        yearCheckBoxList = FXCollections.observableArrayList();
        yearCheckBoxList.clear();

        for (int i = minYear; i <= maxYear; i++) {
            yearCheckBoxList.add(String.valueOf(i));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        faculties = entityDAO.getInformationAboutFaculties();

        this.yearComboBox.setItems(yearCheckBoxList);
        this.yearComboBox.setValue(staticYear);

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

        this.yearComboBoxChanged();
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
        refresh();
    }

    @FXML
    public void yearComboBoxChanged() {
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

    public void refresh() {
        yearComboBoxChanged();
    }
}
