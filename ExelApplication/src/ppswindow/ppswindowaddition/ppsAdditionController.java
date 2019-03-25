package ppswindow.ppswindowaddition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import entity.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ppsAdditionController implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label nameLabel2;
    @FXML
    private Label categoryLabel2;
    @FXML
    private Label yearLabel2;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
    @FXML
    private Label label9;
    @FXML
    private Label label10;
    @FXML
    private Label label11;
    @FXML
    private Label label12;
    @FXML
    private Label label13;
    @FXML
    private Label label14;
    @FXML
    private Label label1_1;
    @FXML
    private Label label2_1;
    @FXML
    private Label label3_1;
    @FXML
    private Label label4_1;
    @FXML
    private Label label5_1;
    @FXML
    private Label label6_1;
    @FXML
    private Label label7_1;
    @FXML
    private Label label8_1;
    @FXML
    private Label label9_1;
    @FXML
    private Label label10_1;

    private Parent loader;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void exit(ActionEvent event) {
        Stage stage = (Stage) yearLabel.getScene().getWindow();
        stage.hide();

        fxmlLoader.setLocation(getClass().getResource("/ppswindow/Pps.fxml"));
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

    public void setLabels(Person person, int[] mass1, double[] mass2, String year) {
        setFirstListLabels(person, mass1, year);
        setSecondListLabels(person, mass2, year);
    }

    private void setFirstListLabels(Person person, int[] mass, String year) {
        Label[] firstListLabels = {label1_1, label2_1, label3_1, label4_1,
                label5_1, label6_1, label7_1, label8_1, label9_1, label10_1};

        this.categoryLabel.setText(person.getpCategory());
        this.nameLabel.setText(person.getpName());
        this.yearLabel.setText(year);

        for (int i = 0; i < mass.length; i++) {

            if (i == 6) {
                label7_1.setText(String.valueOf(mass[i]));
                continue;
            }

            if (mass[i] == 1) {
                firstListLabels[i].setText("+");
            }
        }
    }

    private void setSecondListLabels(Person person, double[] mass, String year) {
        Label[] secondListLabels = {label1, label2, label3, label4, label5,
                label6, label7, label8, label9, label10, label11, label12,
                label13, label14};

        this.categoryLabel2.setText(person.getpCategory());
        this.nameLabel2.setText(person.getpName());
        this.yearLabel2.setText(year);

        for (int i = 0; i < mass.length - 1; i++) {
            if (mass[i] != 0.0 || mass[i] != -1) {
                secondListLabels[i].setText(String.valueOf(mass[i]));
            }
        }

        if (mass[13] == 1) {
            label14.setText("+");
        }
    }
}
