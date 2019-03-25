package facultywindow.constantswindow;

import dbconnection.information_from_db.EntityDAO;
import entity.FacultyConstants;
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
    public Button saveButton;
    private EntityDAO entityDAO = new EntityDAO();

    public FacultyConstants saveInformation() {

        int studyConstant = Integer.parseInt(studyWorkTextField.getText());
        int methodicalConstant
                = Integer.parseInt(methodicalWorkTextField.getText());
        int scienceConstant = Integer.parseInt(scienceWorkTextField.getText());
        int matBaseConstant = Integer.parseInt(matBaseTextField.getText());
        entityDAO.addConstantToDatabase(studyConstant, methodicalConstant,
                scienceConstant, matBaseConstant);

        return new FacultyConstants(studyConstant, methodicalConstant,
                scienceConstant, matBaseConstant);
    }
}
