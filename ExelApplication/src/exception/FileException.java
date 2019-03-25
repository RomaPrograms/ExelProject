package exception;

import javafx.scene.control.Alert;

public class FileException {
    public static void callAlert(String information) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка.");
        alert.setHeaderText(null);
        alert.setContentText(information);
        alert.showAndWait();
    }
}
