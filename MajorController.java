
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MajorController implements Initializable {

    @FXML
    private ComboBox slcMajor;
    @FXML
    private Button btnChangeMajorSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slcMajor.getItems().removeAll(slcMajor.getItems());
        slcMajor.getItems().addAll("Option A", "Option B", "Option C");
    }
}
