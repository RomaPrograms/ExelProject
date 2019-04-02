package exception;

import javafx.scene.control.Alert;

public class FileException {
    public Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public void callAlert(String information) {
        alert.setHeaderText(null);
        alert.setContentText(information);
        alert.show();
    }

    public void closeAlert() {
        alert.close();
    }
}
