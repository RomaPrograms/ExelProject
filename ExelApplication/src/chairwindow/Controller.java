package chairwindow;

import entity.Chair;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public interface Controller {
    void setLabels(Chair chair, double[] mass, String year);

    default void setLabels(Label[] massLabel, Label nameLabel, Label yearLabel,
                           Chair chair, String year, double mass[]) {
        nameLabel.setText(chair.getChName());
        yearLabel.setText(year);

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        for (int i = 0; i < mass.length; i++) {
            massLabel[i].setText(String.valueOf(df.format(mass[i])));
        }
    }

    default void exit(Button backButton, FXMLLoader fxmlLoader, Parent loader) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.hide();

        fxmlLoader.setLocation(getClass().
                getResource("/chairwindow/chair.fxml"));
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
