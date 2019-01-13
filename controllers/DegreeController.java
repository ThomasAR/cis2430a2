package home.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class DegreeController implements Initializable {

    @FXML
    private ComboBox slcDegree;
    @FXML
    private Button btnChangeDegreeSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slcDegree.getItems().removeAll(slcDegree.getItems());
        slcDegree.getItems().addAll("Option A", "Option B", "Option C");
    }
}
