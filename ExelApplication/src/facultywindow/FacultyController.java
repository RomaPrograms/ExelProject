package facultywindow;

import dbconnection.information_from_db.EntityDAO;
import entity.Commission;
import entity.Faculty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
                new PropertyValueFactory<Faculty, String>("fConstantSchoolWork"));
        this.forthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fMethodicalWork"));
        this.fifthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, String>("fConstantMethodicalWork"));
        this.sixthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, String>("fConstantIdWork"));
        this.seventhColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fSinceWork"));
        this.eighthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, String>("fConstantSinceWork"));
        this.ninthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fMatBase"));
        this.tenthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, String>("fConstantMatBase"));
        this.eleventhColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, String>("fConstantVSandVPVO"));
        this.twelfthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, String>("fConstantSMR"));
        this.thirteenthColumn.setCellValueFactory(
                new PropertyValueFactory<Faculty, Double>("fRate"));

        this.thirdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //this.thirdColumn.setOnEditCommit(e -> {
//            Faculty faculty = (Faculty) tableView.getSelectionModel().getSelectedItem();
//            faculty.setfConstantSchoolWork((String) tableView.getEditingCell().getTableColumn().getCellObservableValue(tableView.getSelectionModel().getSelectedItem()).getValue());
//            faculties.get(0).setfConstantSchoolWork((String) tableView.getEditingCell().getTableColumn().getCellObservableValue(tableView.getSelectionModel().getSelectedItem()).getValue());
//            System.out.println((String) tableView.getEditingCell().getTableColumn().getCellObservableValue(tableView.getSelectionModel().getSelectedItem()).getValue());
//            System.out.println(((TableColumn) tableView.getColumns().get(0)).getCellObservableValue(2).getValue());
//            TablePosition pos = (TablePosition) tableView.getSelectionModel().getSelectedCells().get(0);
//            int index = pos.getRow();
//            System.out.println(tableView.getItems().get(index).toString());
//        });

        this.fifthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.fifthColumn.setOnEditCommit(event -> {
//            System.out.println((String) tableView.getEditingCell().getTableColumn().getCellObservableValue(tableView.getSelectionModel().getSelectedItem()).getValue());
//        });

        this.sixthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.sixthColumn.setOnEditCommit(event -> {
//
//        });

        this.eighthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.eighthColumn.setOnEditCommit(event -> {
//
//        });

        this.tenthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.tenthColumn.setOnEditCommit(event -> {
//
//        });

        this.eleventhColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.eleventhColumn.setOnEditCommit(event -> {
//
//        });

        this.twelfthColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.twelfthColumn.setOnEditCommit(event -> {
//
//        });

        this.thirdColumn.setEditable(true);
        this.fifthColumn.setEditable(true);
        this.sixthColumn.setEditable(true);
        this.eighthColumn.setEditable(true);
        this.tenthColumn.setEditable(true);
        this.eleventhColumn.setEditable(true);
        this.twelfthColumn.setEditable(true);
        this.tableView.setEditable(true);
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
//        refresh();
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
//
//    public void refresh() {
//        yearComboBoxChanged();
//    }

    public void saveButtonPressed(ActionEvent event) {
        for(Faculty faculty : (ObservableList<Faculty>) tableView.getItems()) {
            Commission commission = new Commission();
            commission.setfConstantSchoolWork(faculty.getfConstantSchoolWork());
            commission.setfConstantIdWork(faculty.getfConstantIdWork());
            commission.setfConstantMatBase(faculty.getfConstantMatBase());
            commission.setfConstantMethodicalWork(faculty.getfConstantMethodicalWork());
            commission.setfConstantSinceWork(faculty.getfConstantSinceWork());
            commission.setfConstantSMR(faculty.getfConstantSMR());
            commission.setfConstantVSandVPVO(faculty.getfConstantVSandVPVO());
            commission.setChairUnivName(faculty.getfName());
            commission.setChairYear(faculty.getfYear());
            entityDAO.updateCommissionByCairNameAndChairYear(commission);
        }
        faculties = entityDAO.getInformationAboutFaculties();
        yearComboBoxChanged();
    }

    public void constantSchoolValueChanged(TableColumn.CellEditEvent<Faculty, String> cellEditEvent) {
        Faculty faculty = (Faculty) tableView.getSelectionModel().getSelectedItem();
        faculty.setfConstantSchoolWork(cellEditEvent.getNewValue());
    }

    public void constantMethodicalValueChanged(TableColumn.CellEditEvent<Faculty, String> cellEditEvent) {
        Faculty faculty = (Faculty) tableView.getSelectionModel().getSelectedItem();
        faculty.setfConstantMethodicalWork(cellEditEvent.getNewValue());
    }

    public void constantIdeologyValueChanged(TableColumn.CellEditEvent<Faculty, String> cellEditEvent) {
        Faculty faculty = (Faculty) tableView.getSelectionModel().getSelectedItem();
        faculty.setfConstantIdWork(cellEditEvent.getNewValue());
    }

    public void constantScienceValueChanged(TableColumn.CellEditEvent<Faculty, String> cellEditEvent) {
        Faculty faculty = (Faculty) tableView.getSelectionModel().getSelectedItem();
        faculty.setfConstantSinceWork(cellEditEvent.getNewValue());
    }

    public void constantMTBValueChanged(TableColumn.CellEditEvent<Faculty, String> cellEditEvent) {
        Faculty faculty = (Faculty) tableView.getSelectionModel().getSelectedItem();
        faculty.setfConstantMatBase(cellEditEvent.getNewValue());
    }

    public void constantOBVSValueChanged(TableColumn.CellEditEvent<Faculty, String> cellEditEvent) {
        Faculty faculty = (Faculty) tableView.getSelectionModel().getSelectedItem();
        faculty.setfConstantVSandVPVO(cellEditEvent.getNewValue());
    }

    public void constantCMPValueChanged(TableColumn.CellEditEvent<Faculty, String> cellEditEvent) {
        Faculty faculty = (Faculty) tableView.getSelectionModel().getSelectedItem();
        faculty.setfConstantSMR(cellEditEvent.getNewValue());
    }
}
