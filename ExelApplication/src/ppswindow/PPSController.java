package ppswindow;

import dbconnection.information_from_db.EntityDAO;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import entity.Person;
import ppswindow.ppswindowaddition.ppsAdditionController;
import reader.TableFileReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class PPSController implements Initializable {
    @FXML
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> chairColumn;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, String> rankColumn;
    @FXML
    private TableColumn<Person, Double> rateColumn;
    @FXML
    private TableColumn<Person, String> categoryColumn;
    @FXML
    private TableColumn<Person, String> rateQualColumn;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private ComboBox categoryComboBox;
    @FXML
    private ComboBox yearComboBox;
    @FXML
    private TextField searchField;
    @FXML
    private RadioButton lastNameRadioButton;
    @FXML
    private RadioButton chairRadioButton;
    @FXML
    private Label searchLabel;

    ObservableList<Person> persons = FXCollections.observableArrayList();

    ObservableList<String> categoryCheckBoxList =
            FXCollections.observableArrayList("любая", "профессор",
                    "доцент", "преподаватель", "ст. преподаватель",
                    "нач. цикла", "нач. кафедры");

    public static ObservableList<String> yearCheckBoxList
            = FXCollections.observableArrayList("2019", "2018", "2017",
            "2016", "2015");


    private Parent loader;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private FilteredList<Person> filter;
    private ppsAdditionController controller;
    private TableFileReader tableFileReader;
    private static String staticYear = "выбрать год";
    private static String staticSearchLabel = "Фамилия: ";
    private static String staticCategory = "любая";
    private static String staticSearchFild = "";
    private static EntityDAO entityDAO = new EntityDAO();

    public void setYearCheckBoxList(int minYear, int maxYear) {
        yearCheckBoxList.clear();

        for (int i = minYear; i <= maxYear; i++) {
            yearCheckBoxList.add(String.valueOf(i));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.categoryComboBox.setItems(categoryCheckBoxList);
        this.yearComboBox.setItems(yearCheckBoxList);
        this.categoryComboBox.setValue(staticCategory);
        this.yearComboBox.setValue(staticYear);
        this.searchLabel.setText(staticSearchLabel);
        this.searchField.setText(staticSearchFild);
        if (staticSearchLabel.equals("Фамилия: ")) {
            lastNameRadioButton.setSelected(true);
        } else {
            chairRadioButton.setSelected(true);
        }

        tableView.setEditable(true);

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getClickCount() == 2 && tableView.getSelectionModel().
                        getSelectedItem() != null) {
                    Person person = tableView.getSelectionModel().
                            getSelectedItem();
                    String path
                            = entityDAO.findPathToFileByChairAndYear(staticYear,
                            person.getpChair());

                    tableFileReader = new TableFileReader(new File(path));

                    int[] mass1 = tableFileReader.GetAdditionalInfo1(person);
                    double[] mass2 = tableFileReader.GetAdditionalInfo2(person);
                    ppsInformation(person, mass1, mass2, staticYear);
                }
            }
        });

        this.idColumn.setCellValueFactory(
                new PropertyValueFactory<>("pId"));
        this.chairColumn.setCellValueFactory(
                new PropertyValueFactory<>("pChair"));
        this.nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("pName"));
        this.rateColumn.setCellValueFactory(
                new PropertyValueFactory<>("pCategory"));
        this.categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("pRate"));
        this.rankColumn.setCellValueFactory(
                new PropertyValueFactory<>("pRank"));
        this.rateQualColumn.setCellValueFactory(
                new PropertyValueFactory<>("pRateQual")
        );

        this.yearComboBoxChanged();
        if (!staticSearchFild.isEmpty()) {
            filter.setPredicate((Predicate<? super Person>) (Person person) ->{
                if (person.getpCategory().equals(staticCategory)) {
                    if (chairRadioButton.isSelected()
                            && person.getpChair().contains(staticSearchFild)) {
                        return true;
                    } else {
                        if (person.getpName().contains(staticSearchFild)) {
                            return true;
                        }
                    }
                }
                return false;
            });

            SortedList<Person> sort = new SortedList<>(filter);
            sort.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sort);

        } else {
           this.categoryComboBoxChanged();
        }

    }

    @FXML
    public void categoryComboBoxChanged() {
        String category = categoryComboBox.getValue().toString();
        if (!category.equals("любая")) {
            filter.setPredicate((Predicate<? super Person>) (Person person) -> {
                if (person.getpCategory().equals(category)) {
                    return true;
                }
                return false;
            });

            SortedList<Person> sort = new SortedList<>(filter);
            sort.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sort);
        } else {
            filter.setPredicate((Predicate<? super Person>) (Person person) -> {
                return true;
            });

            SortedList<Person> sort = new SortedList<>(filter);
            sort.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sort);
        }

        staticCategory = category;
    }

    int t = 0;
    @FXML
    public void yearComboBoxChanged() {
        filter.clear();
//        ObservableList<Person> items = tableView.getItems();
//        if (t == 1) {
//            System.out.println(3);
//        }
//        t++;
//
//        if (items.size() != 0) {
//            items.clear();
//        }
        String year = yearComboBox.getValue().toString();    //FXMLLoader fxmlLoader;
        staticYear = year;
        persons = entityDAO.findPersonsByYear(year);
        filter = new FilteredList(persons, e -> true);
        //search();
        tableView.setItems(persons);
    }

    @FXML
    public void search() {
        staticSearchFild = searchField.getText();

        filter.setPredicate((Predicate<? super Person>) (Person person) -> {
            return sorting(staticSearchFild, person);
        });

        SortedList<Person> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
    }

    public boolean sorting(String newValue, Person person) {
        if (staticCategory.equals("любая")) {
            if (lastNameRadioButton.isSelected()) {
                if (newValue.isEmpty() && person.getpCategory().
                        equals(staticCategory)) {
                    return true;
                } else {
                    if (person.pNameProperty().toString().contains(newValue)) {
                        return true;
                    }
                }
            } else {
                if (newValue.isEmpty() && person.getpCategory().
                        equals(staticCategory)) {
                    return true;
                } else {
                    if (person.pChairProperty().toString().contains(newValue)) {
                        return true;
                    }
                }
            }
        } else {
            if (lastNameRadioButton.isSelected()) {
                if (newValue.isEmpty() && person.getpCategory().
                        equals(staticCategory)) {
                    return true;
                } else {
                    if (person.pNameProperty().toString().contains(newValue)
                            && person.getpCategory().
                            equals(categoryComboBox.getValue())) {
                        return true;
                    }
                }
            } else {
                if (newValue.isEmpty() && person.getpCategory().
                        equals(staticCategory)) {
                    return true;
                } else {
                    if (person.pChairProperty().toString().contains(newValue)
                            && person.getpCategory().equals(staticCategory)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @FXML
    private void radioSelect() {
        if (lastNameRadioButton.isSelected()) {
            searchLabel.setText("Фамилия: ");
            staticSearchLabel = "Фамилия: ";
        } else {
            if (chairRadioButton.isSelected()) {
                searchLabel.setText("Кафедра: ");
                staticSearchLabel = "Кафедра: ";
            }
        }
    }

    public void ppsInformation(Person person, int[] mass1, double[] mass2, String year) {
        Stage stage = (Stage) this.categoryComboBox.getScene().getWindow();
        stage.hide();

        fxmlLoader.setLocation(getClass().
                getResource("/ppswindow/ppswindowaddition/ppsAddition.fxml"));
        try {
            loader = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller = fxmlLoader.getController();
        controller.setLabels(person, mass1, mass2, year);
        Stage newStage = new Stage();
        newStage.setTitle("Информация о ППС");
        newStage.setScene(new Scene(loader));
        newStage.show();
    }

    @FXML
    public void exit(ActionEvent event) {
        Stage stage = (Stage) chairRadioButton.getScene().getWindow();
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
