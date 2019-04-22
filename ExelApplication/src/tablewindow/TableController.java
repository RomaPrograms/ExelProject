package tablewindow;

import java.sql.Connection;
import dbconnection.DbConnection;
import dbconnection.information_from_db.EntityDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private ListView<String> tableList;

    private ObservableList<String> listForAddingInformation;
    private String sqlQueryForGettingTable = "SELECT * FROM addedfiles";
    private static EntityDAO entityDAO = new EntityDAO();

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        addingInformationToList();
        tableList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addingInformationToList() {
        try {
            Connection connection = DbConnection.getConnection();
            this.listForAddingInformation = FXCollections.observableArrayList();

            try {
                ResultSet resultSet = connection.createStatement()
                        .executeQuery(sqlQueryForGettingTable);
                while (resultSet.next()) {
                    listForAddingInformation
                            .add(resultSet.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            tableList.setItems(listForAddingInformation);
            tableList.getSelectionModel()
                    .setSelectionMode(SelectionMode.MULTIPLE);

        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteTable() {
        ObservableList<String> listForDeletingInformation;
        listForDeletingInformation =
                tableList.getSelectionModel().getSelectedItems();

        entityDAO.deleteDataFromDatabase(listForDeletingInformation);
        addingInformationToList();
    }
}
