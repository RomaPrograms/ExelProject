package facultywindow.constantswindow;

import dbconnection.information_from_db.EntityDAO;
import facultywindow.FacultyController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ConstantController {
    @FXML
    public TextField studyWorkTextField;
    @FXML
    public TextField methodicalWorkTextField;
    @FXML
    public TextField scienceWorkTextField;
    @FXML
    public TextField matBaseTextField;
    @FXML
    public TextField ideologWorkTextField;
    @FXML
    public TextField vsAndOBVSTextField;
    @FXML
    public TextField cmpTextField;
    @FXML
    public Button saveButton;
    private static final EntityDAO entityDAO = new EntityDAO();
    private static final FacultyController facultyController = new FacultyController();

    public void saveInformation() {

        double studyConstant;
        double methodicalConstant;
        double scienceConstant;
        double matBaseConstant;
        double ideologyConstant;
        double vs;
        double cmp;

        if (!studyWorkTextField.getText().isEmpty()) {
            studyConstant = Double.parseDouble(studyWorkTextField.getText());
        } else {
            studyConstant = -1;
        }
        if (!methodicalWorkTextField.getText().isEmpty()) {
            methodicalConstant
                    = Double.parseDouble(methodicalWorkTextField.getText());
        } else {
            methodicalConstant = -1;
        }
        if (!scienceWorkTextField.getText().isEmpty()) {
            scienceConstant = Double.parseDouble(scienceWorkTextField.getText());
        } else {
            scienceConstant = -1;
        }
        if (!matBaseTextField.getText().isEmpty()) {
            matBaseConstant = Double.parseDouble(matBaseTextField.getText());
        } else {
            matBaseConstant = -1;
        }
        if (!ideologWorkTextField.getText().isEmpty()) {
            ideologyConstant = Double.parseDouble(ideologWorkTextField.getText());
        } else {
            ideologyConstant = -1;
        }
        if (!vsAndOBVSTextField.getText().isEmpty()) {
            vs = Double.parseDouble(vsAndOBVSTextField.getText());
        } else {
            vs = -1;
        }
        if (!cmpTextField.getText().isEmpty()) {
            cmp = Double.parseDouble(cmpTextField.getText());
        } else {
            cmp = -1;
        }

        entityDAO.addConstantToDatabase(studyConstant, methodicalConstant,
                ideologyConstant, scienceConstant, matBaseConstant, vs, cmp);

        facultyController.faculties.clear();
        facultyController.faculties = entityDAO.getInformationAboutFaculties();
        //facultyController.yearComboBoxChanged();
    }
}
