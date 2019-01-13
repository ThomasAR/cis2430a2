package home.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button btnConnect;
    @FXML
    private TextField txtConnectUser;
    @FXML
    private TextField txtConnectPass;
    @FXML
    private Button btnNewConnect;

    @FXML
    private Button btnAddCourse;
    @FXML
    private TextField txtAddCourseCode;
    @FXML
    private TextField txtAddTitle;
    @FXML
    private TextField txtAddCredit;
    @FXML
    private TextField txtReqCode;
    @FXML
    private CheckBox chkAddF;
    @FXML
    private CheckBox chkAddW;
    @FXML
    private TableView tableAddRequired;
    @FXML
    private Button btnAddReq;
    @FXML
    private Button btnAddNewCourse;

    @FXML
    private Button btnAddDegree;

    private int activeMenu = 0;

    //Menus
    //0: Main Menu
    //1: Connect
    //2: Add Course
    //3: Add Degree

    @FXML
    private Pane menu0Main;
    @FXML
    private Pane menu1Connect;
    @FXML
    private Pane menu2AddCourse;
    @FXML
    private Pane menu3AddDegree;

    @FXML
    private void handleMenuClicks(javafx.event.ActionEvent mouseEvent)
    {
        if (mouseEvent.getSource() == btnConnect) {
            switchMenu(activeMenu, false);
            switchMenu(1, true); //load menu1Degree
        } else if (mouseEvent.getSource() == btnAddCourse) {
            switchMenu(activeMenu, false);
            switchMenu(2, true); //load menu1Degree
        } else if (mouseEvent.getSource() == btnAddDegree) {
            switchMenu(activeMenu, false);
            switchMenu(3, true); //load menu1Degree
        }
    }

    @FXML
    private void handleConnectClicks(javafx.event.ActionEvent mouseEvent)
    {
        if (mouseEvent.getSource() == btnNewConnect) {
            String user = txtConnectUser.getText();
            String pass = txtConnectPass.getText();
        }
    }
    @FXML
    private void handleNewCourseClicks(javafx.event.ActionEvent mouseEvent)
    {
        if (mouseEvent.getSource() == btnAddNewCourse) {
            String courseCode = txtAddCourseCode.getText();
            String title = txtAddTitle.getText();
            String credit = txtAddCredit.getText();
            String semesterOffered = "";

            if(chkAddF.isSelected() && chkAddW.isSelected())
            {
                semesterOffered = "B";
            }
            else if(chkAddF.isSelected())
            {
                semesterOffered = "F";
            }
            else if(chkAddW.isSelected())
            {
                semesterOffered = "W";
            }

        } else if (mouseEvent.getSource() == btnAddNewCourse) {
            String courseCode = txtReqCode.getText();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void switchMenu(int menuNumber, boolean state)
    {
//        Expression e = new ExpressionBuilder("menu(x).setVisible(state)").variables("x").build().setVariable("x", menuNumber);
//        e.evaluate();
        activeMenu = menuNumber;

        if(menuNumber == 0)
        {
            menu0Main.setVisible(state);
        }
        else if(menuNumber == 1)
        {
            menu1Connect.setVisible(state);
        }
        else if(menuNumber == 2)
        {
            menu2AddCourse.setVisible(state);
        }
        else if(menuNumber == 3)
        {
            menu3AddDegree.setVisible(state);
        }
    }
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
