package chairwindow;

import dbconnection.information_from_db.EntityDAO;
import entity.Chair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import reader.TableFileReader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChairController implements Initializable {
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
    private Button backButton;
    @FXML
    private ComboBox yearComboBox;
    @FXML
    private TableView<Chair> tableView;

    private static ObservableList<String> yearCheckBoxList;
    private Parent loader;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Controller controller;
    private static String staticYear = "выбрать год";
    private static EntityDAO entityDAO = new EntityDAO();

    public static void setYearCheckBoxList(int minYear, int maxYear) {
        yearCheckBoxList = FXCollections.observableArrayList();
        yearCheckBoxList.clear();

        for (int i = minYear; i <= maxYear; i++) {
            yearCheckBoxList.add(String.valueOf(i));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        yearComboBox.setItems(yearCheckBoxList);
        yearComboBox.setValue(staticYear);
        tableView.setEditable(true);

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getClickCount() == 2 && tableView.getSelectionModel().
                        getSelectedItem() != null) {
                    String pathToFile;
                    String title;
                    Stage stage = (Stage) yearComboBox.getScene().getWindow();
                    TablePosition tablePosition = tableView.
                            getSelectionModel().getSelectedCells().get(0);
                    int row = tablePosition.getRow();
                    Chair chair = tableView.getItems().get(row);
                    TableColumn tableColumn = tablePosition.getTableColumn();
                    double[] mass = null;
                    TableFileReader tableFileReader = new TableFileReader(
                            new File(entityDAO
                                    .findPathToFileByChairAndYear(staticYear,
                                            chair.getChUnivName() + "-" + chair.getChName())));

                    if (!tableColumn.getText().equals("Номер кафедры")
                            && !tableColumn.getText().equals("Наименование\nкафедры")) {
                        if (tableColumn.getText().equals("НПП")) {
                            pathToFile = "/chairwindow/nppwindow/npp.fxml";
                            title = "НПП";
                            mass = tableFileReader.GetAdditionalInfoForChNpp();
                        } else {
                            if (tableColumn.getText().equals("Учебная\nработа")) {
                                pathToFile = "/chairwindow/studywindow/study.fxml";
                                title = "Учебная работа";
                                mass = tableFileReader.GetAdditionalInfoForChStudyWork();
                            } else {
                                if (tableColumn.getText().equals("Методическая\nработа")) {
                                    pathToFile = "/chairwindow/methodicalwindow/methodical.fxml";
                                    title = "Методическая работа";
                                    mass = tableFileReader.GetAdditionalInfoForChMethodicalWork();
                                } else {
                                    if (tableColumn.getText().equals("Научная\nдеятельность")) {
                                        pathToFile = "/chairwindow/sincewindow/since.fxml";
                                        title = "Научная деятельность";
                                        mass = tableFileReader.GetAdditionalInfoForChSinceWork();
                                    } else {
                                        if (tableColumn.getText().equals("Мат-тех\nбаза")) {
                                            pathToFile = "/chairwindow/matbasewindow/matbase.fxml";
                                            title = "Материально-техническая база";
                                            mass = tableFileReader.GetAdditionalInfoForChMatBase();
                                        } else {
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        stage.hide();

                        fxmlLoader.setLocation(getClass().
                                getResource(pathToFile));
                        try {
                            loader = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        controller = fxmlLoader.getController();
                        controller.setLabels(chair, mass, staticYear);
                        Stage newStage = new Stage();
                        newStage.setTitle(title);
                        newStage.setScene(new Scene(loader));
                        newStage.show();
                    }
                }
            }
        });

        this.firstColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chUnivName"));
        this.secondColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chName"));
        this.thirdColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chNpp"));
        this.forthColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, Double>("chStudyWork"));
        this.fifthColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chMethodicalWork"));
        this.sixthColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chSinceWork"));
        this.seventhColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chMatBase"));
        this.eighthColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chIdWork"));
        this.ninthColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chSecurity"));
        this.tenthColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chRate"));
        this.eleventhColumn.setCellValueFactory(
                new PropertyValueFactory<Chair, String>("chRateQual"));

        this.yearComboBoxChanged();
    }

    @FXML
    public void yearComboBoxChanged() {
        tableView.getItems().clear();
        staticYear = yearComboBox.getValue().toString();

        tableView.setItems(entityDAO.findChairsByYear(staticYear));
    }

    public void exit(ActionEvent event) {
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
