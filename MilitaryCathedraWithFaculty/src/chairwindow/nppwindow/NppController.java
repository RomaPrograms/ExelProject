package chairwindow.nppwindow;

import chairwindow.Controller;
import entity.Chair;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class NppController implements Controller {
    @FXML
    public Label label1;
    @FXML
    public Label label2;
    @FXML
    public Label label3;
    @FXML
    public Label label4;
    @FXML
    public Label label5;
    @FXML
    public Button backButton;
    @FXML
    public Label yearLabel;
    @FXML
    public Label nameLabel;

    private Parent loader;
    private FXMLLoader fxmlLoader = new FXMLLoader();

    public void setLabels(Chair chair, double[] mass, String year) {
        Label[] massLabel = {label1, label2, label3, label4, label5};
        setLabels(massLabel, nameLabel, yearLabel, chair, year, mass);
    }

    public void exit() {
        exit(backButton, fxmlLoader, loader);
    }
}
