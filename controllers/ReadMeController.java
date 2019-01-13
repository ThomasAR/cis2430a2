package home.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ReadMeController implements Initializable {

    @FXML
    private Label lblReadMe;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try (Scanner scanner = new Scanner(new File("src/home/resources/README.txt"))) {

            while (scanner.hasNext())
                //System.out.println(scanner.next());
                lblReadMe.setText(lblReadMe.getText() + "\n" + scanner.next());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
