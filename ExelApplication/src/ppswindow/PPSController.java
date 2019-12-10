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
import java.util.*;
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
                    "доцент", "преподаватель", "ст. преподаватель");

    private static ObservableList<String> yearCheckBoxList;


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
    private static Map<String, List<String>> categoryMap = new HashMap<>();

    public static void setYearCheckBoxList(int minYear, int maxYear) {
        yearCheckBoxList = FXCollections.observableArrayList();
        yearCheckBoxList.clear();

        for (int i = minYear; i <= maxYear; i++) {
            yearCheckBoxList.add(String.valueOf(i));
        }
    }

    static {
        List<String> list1 = new ArrayList<>();
        list1.add("нач. кафедры");
        list1.add("нач. цикла - профессор");
        list1.add("профессор");
        categoryMap.put("профессор", list1);
        list1 = new ArrayList<>();
        list1.add("зам. нач. кафедры");
        list1.add("доцент");
        list1.add("нач. цикла");
        categoryMap.put("доцент", list1);
        list1 = new ArrayList<>();
        list1.add("ст. преподаватель");
        categoryMap.put("ст. преподаватель", list1);
        list1 = new ArrayList<>();
        list1.add("преподаватель");
        categoryMap.put("преподаватель", list1);
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
                            person.getpPath());

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

        this.yearComboBoxChangedByMe();
        if (!staticSearchFild.isEmpty()) {
            filter.setPredicate((Predicate<? super Person>) (Person person) -> {
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
    public void yearComboBoxChanged() {
        staticYear = yearComboBox.getValue().toString();
        persons.clear();
        persons = entityDAO.findPersonsByYear(staticYear);
        filter = new FilteredList(persons, e -> true);
        staticCategory = "любая";
        this.categoryComboBox.setValue(staticCategory);
        staticSearchFild = "";
        this.searchField.setText(staticSearchFild);
        tableView.setItems(persons);
    }

    public void yearComboBoxChangedByMe() {
        staticYear = yearComboBox.getValue().toString();
        persons.clear();
        persons = entityDAO.findPersonsByYear(staticYear);
        filter = new FilteredList(persons, e -> true);
        this.categoryComboBox.setValue(staticCategory);
        this.searchField.setText(staticSearchFild);
        tableView.setItems(persons);
    }

    @FXML
    public void categoryComboBoxChanged() {
        String category = categoryComboBox.getValue().toString();
        if (!category.equals("любая")) {
            final List<String> list = categoryMap.get(category);
            filter.setPredicate((Predicate<? super Person>) (Person person) -> {
                for(int i = 0; i < list.size(); i++) {
                    if (person.getpCategory().equals(list.get(i))) {
                        return true;
                    }
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

        if (!staticSearchFild.isEmpty()) {
           staticSearchFild = "";
           searchField.setText(staticSearchFild);
        }

        staticCategory = category;
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
            final List<String> list = categoryMap.get(staticCategory);
            if (lastNameRadioButton.isSelected()) {
                if (newValue.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (person.getpCategory().equals(list.get(i))) {
                            return true;
                        }
                    }
                } else {
                    if (person.pNameProperty().toString().contains(newValue)) {
                        for (int i = 0; i < list.size(); i++) {
                            if (person.getpCategory().equals(list.get(i))) {
                                return true;
                            }
                        }
                    }
                }
            } else {
                if (newValue.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (person.getpCategory().equals(list.get(i))) {
                            return true;
                        }
                    }
                } else {
                    if (person.pChairProperty().toString().contains(newValue)) {
                        for (int i = 0; i < list.size(); i++) {
                            if (person.getpCategory().equals(list.get(i))) {
                                return true;
                            }
                        }
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
        stage.close();

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
