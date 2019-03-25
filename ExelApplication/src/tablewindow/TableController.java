package tablewindow;

import com.mysql.jdbc.Connection;
import dbconnection.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import reader.FileManager;
import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private ListView<String> tableList;

    private ObservableList<String> listForAddingInformation;

    private String sqlQueryForGettingTable = "SELECT * FROM addedfiles";
    private String sqlQueryForDeletingLineAddedFiles =
            "DELETE FROM addedfiles where pathToFile = ?";
    private String sqlQueryForDeletingLinePersons =
            "DELETE FROM persons where pyear = ? and pchair = ?";
    private String sqlQueryForDeletingLineChairs =
            "DELETE FROM chairs where chairYear = ? and chairName = ?";

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        addingInformationToList();
        tableList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void addingInformationToList() {
        try {
            Connection connection = (Connection) DbConnection.getConnection();
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

        } catch (NullPointerException ex) { //calls when resultSet is empty.
            ex.printStackTrace();
        }
    }

    public void deleteTable() {
        ObservableList<String> listForDeletingInformation;
        listForDeletingInformation =
                tableList.getSelectionModel().getSelectedItems();

        deleteTableFromDatabase(listForDeletingInformation);
        addingInformationToList();
    }

    public void deleteTableFromDatabase(ObservableList<String>
                                                listForDeletingInformation) {
        FileManager fileManager = new FileManager();
        Connection connection = (Connection) DbConnection.getConnection();

        try {
            PreparedStatement preparedStatement1 =
                    connection.prepareStatement(sqlQueryForDeletingLineAddedFiles);
            PreparedStatement preparedStatement2 =
                    connection.prepareStatement(sqlQueryForDeletingLinePersons);
            PreparedStatement preparedStatement3 =
                    connection.prepareStatement(sqlQueryForDeletingLineChairs);

            for (int i = 0; i < listForDeletingInformation.size(); i++) {
                String addingResult = listForDeletingInformation.get(i);
                int lastIndex = addingResult.lastIndexOf('.');
                int lastIndex1 = addingResult.lastIndexOf('\\');
                String newAddingResult =
                        addingResult.substring(0, lastIndex1);
                int lastIndex2 = newAddingResult.lastIndexOf('\\');

                int year = Integer.parseInt(newAddingResult
                        .substring(lastIndex2 + 1));
                String chair = addingResult
                        .substring(lastIndex1 + 1, lastIndex);
                preparedStatement1.setString(
                        1, addingResult);
                preparedStatement2.setInt(1, year);
                preparedStatement2.setString(2, chair);
                preparedStatement3.setInt(1, year);
                preparedStatement3.setString(2, chair);

                preparedStatement3.execute();
                preparedStatement2.execute();
                preparedStatement1.execute();

                fileManager.DeleteFile(new File(addingResult));
            }

            preparedStatement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
