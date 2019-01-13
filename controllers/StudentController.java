package home.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.chart.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import home.Main;

public class StudentController implements Initializable {

    @FXML
    private Button btnDegree;
    @FXML
    private Button btnProgress;
    @FXML
    private Button btnPlanOfStudy;
    @FXML
    private Button btnTranscript;
    @FXML
    private MenuItem menuFileSave;
    @FXML
    private MenuItem menuFileOpen;
    @FXML
    private MenuItem menuFileNew;
    @FXML
    private MenuItem menuFileNewSave;
    @FXML
    private MenuItem menuHelpRead;
    @FXML
    private TextField txtNewStudentNumber;
    @FXML
    private TextField txtNewStudentFName;
    @FXML
    private TextField txtNewStudentLName;
    @FXML
    private Button btnNewStudentSave;

    @FXML
    private Button btnDegreeViewCourses;
    @FXML
    private Button btnDegreeBack;
    @FXML
    private Button btnChangeDegree;
    @FXML
    private Button btnChangeMajor;
    @FXML
    private Button btnDegreeViewPrereqs;


    @FXML
    private ScatterChart sctProgressMarks;

    @FXML
    private Button btnPOSAdd;
    @FXML
    private TextField txtPOSAddCourseCode;
    @FXML
    private TextField txtPOSAddSemester;
    @FXML
    private Button btnPOSRemove;
    @FXML
    private TextField txtPOSRemoveCourseCode;
    @FXML
    private TextField txtPOSRemoveSemester;
    @FXML
    private Button btnPOSViewUnadded;
    @FXML
    private Button btnPOSFuturePrereqs;
    @FXML
    private PieChart POSPieChart;
    @FXML
    private TableView tablePlanOfStudy;
    @FXML
    private TableColumn POSCode;
    @FXML
    private TableColumn POSTitle;
    @FXML
    private TableColumn POSCredit;
    @FXML
    private TableColumn POSSemester;


    @FXML
    private Button btnTranscriptAdd;
    @FXML
    private TextField txtTranscriptAddCourseCode;
    @FXML
    private TextField txtTranscriptAddSemester;
    @FXML
    private TextField txtTranscriptAddGrade;
    @FXML
    private TextField txtTranscriptAddStatus;
    @FXML
    private Button btnTranscriptRemove;
    @FXML
    private TextField txtTranscriptRemoveCourseCode;
    @FXML
    private TextField txtTranscriptRemoveSemester;
    @FXML
    private Button btnTranscriptChangeGrade;
    @FXML
    private TextField txtTranscriptEditCourseCode;
    @FXML
    private TextField txtTranscriptEditSemester;
    @FXML
    private TextField txtTranscriptEditGrade;
    @FXML
    private Button btnTranscriptUnaddedCourses;
    @FXML
    private TableView tableTranscript;
    @FXML
    private TableColumn transcriptCode;
    @FXML
    private TableColumn transcriptTitle;
    @FXML
    private TableColumn transcriptGrade;
    @FXML
    private TableColumn transcriptCredit;
    @FXML
    private TableColumn transcriptSemester;
    @FXML
    private TableColumn transcriptStatus;


    private int activeMenu = 0;

    //Menus
    //1: Degree
    //2: Progress
    //3: Plan of Study
    //4: Transcript
    //5: View Degree Courses

    @FXML
    private Pane menu0Main;
    @FXML
    private VBox menu1Degree;
    @FXML
    private VBox menu2Progress;
    @FXML
    private VBox menu3PlanOfStudy;
    @FXML
    private Pane menu4Transcript;
    @FXML
    private VBox menu5DegreeCourses;

    @FXML
    private void handleMenuClicks(javafx.event.ActionEvent mouseEvent) {

        if (mouseEvent.getSource() == btnDegree)
        {
            switchMenu(activeMenu, false);
            switchMenu(1, true); //load menu1Degree
        } else if (mouseEvent.getSource() == btnProgress)
        {
            switchMenu(activeMenu, false);
            switchMenu(2, true); //load menu1Degree
            initializeProgress();
        }
        else if (mouseEvent.getSource() == btnPlanOfStudy)
        {
            switchMenu(activeMenu, false);
            switchMenu(3, true); //load menu1Degree
            initializeTablePlanOfStudy();
        }
        else if (mouseEvent.getSource() == btnTranscript)
        {
            switchMenu(activeMenu, false);
            switchMenu(4, true); //load menu1Degree
            initializeTableTranscript();
        }
        else if (mouseEvent.getSource() == menuFileOpen)
        {

        }
        else if (mouseEvent.getSource() == menuFileSave)
        {

        }
        else if (mouseEvent.getSource() == menuFileNew)
        {

        }
        else if (mouseEvent.getSource() == menuFileNewSave)
        {

        }
        else if (mouseEvent.getSource() == menuHelpRead)
        {
            loadStage("/home/fxml/ReadMe.fxml");
        }
    }

    @FXML
    private void handleDegreeMenuClicks(javafx.event.ActionEvent mouseEvent)
    {
        if (mouseEvent.getSource() == btnDegreeViewCourses)
        {
            switchMenu(activeMenu, false);
            switchMenu(5, true); //load menu1Degree

        }
        else if(mouseEvent.getSource() == btnDegreeBack)
        {
            switchMenu(activeMenu, false);
            switchMenu(1, true);
        }
        else if(mouseEvent.getSource() == btnChangeDegree)
        {
            loadStage("/home/fxml/PopupDegree.fxml");
            //initializeDegreeDropdown();
        }
        else if(mouseEvent.getSource() == btnChangeMajor)
        {
            loadStage("/home/fxml/PopupMajor.fxml");
        }
        else if(mouseEvent.getSource() == btnDegreeViewPrereqs)
        {
            loadStage("/home/fxml/PopupDegree.fxml");
        }
    }

    @FXML
    private void handlePOSMenuClicks(javafx.event.ActionEvent mouseEvent)
    {
        if (mouseEvent.getSource() == btnPOSAdd)
        {
            String courseCode = txtPOSAddCourseCode.getText();
            String semester = txtPOSAddSemester.getText();
            System.out.println(courseCode + semester);
            initializeTablePlanOfStudy();
        }
        else if(mouseEvent.getSource() == btnPOSRemove)
        {
            String courseCode = txtPOSRemoveCourseCode.getText();
            String semester = txtPOSRemoveSemester.getText();
            System.out.println(courseCode + semester);
        }
        else if(mouseEvent.getSource() == btnPOSViewUnadded)
        {
            loadStage("/home/fxml/PopupDegree.fxml");
        }
        else if(mouseEvent.getSource() == btnPOSFuturePrereqs)
        {
            loadStage("/home/fxml/PopupDegree.fxml");
        }
    }

    @FXML
    private void handleTranscriptMenuClicks(javafx.event.ActionEvent mouseEvent)
    {
        if (mouseEvent.getSource() == btnTranscriptAdd)
        {
            String courseCode = txtTranscriptAddCourseCode.getText();
            String semesterTaken = txtTranscriptAddSemester.getText();
            String grade = txtTranscriptAddGrade.getText();
            String status = txtTranscriptAddStatus.getText();
            System.out.println(courseCode + semesterTaken + grade + status);
        }
        else if(mouseEvent.getSource() == btnTranscriptRemove)
        {
            String courseCode = txtTranscriptRemoveCourseCode.getText();
            String semesterTaken = txtTranscriptRemoveSemester.getText();
            System.out.println(courseCode + semesterTaken);
        }
        else if(mouseEvent.getSource() == btnTranscriptChangeGrade)
        {
            String courseCode = txtTranscriptEditCourseCode.getText();
            String semesterTaken = txtTranscriptEditSemester.getText();
            String grade = txtTranscriptEditGrade.getText();
            System.out.println(courseCode + semesterTaken + grade);
        }
        else if(mouseEvent.getSource() == btnTranscriptUnaddedCourses)
        {
            loadStage("/home/fxml/PopupDegree.fxml");
        }
    }

    @FXML
    private void initializeTableTranscript()
    {
        // final ObservableList<Course> data = FXCollections.observableArrayList(
        //         new Course("CIS1500", "Programming", "89", "0.5", "F17", "Complete"),
        //         new Course("CIS2500", "Programming2", "79", "0.5", "W17", "Complete")
        );

        transcriptCode.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
        transcriptTitle.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        transcriptGrade.setCellValueFactory(new PropertyValueFactory<Course, String>("grade"));
        transcriptCredit.setCellValueFactory(new PropertyValueFactory<Course, String>("credit"));
        transcriptSemester.setCellValueFactory(new PropertyValueFactory<Course, String>("semester"));
        transcriptStatus.setCellValueFactory(new PropertyValueFactory<Course, String>("status"));

        tableTranscript.setItems(data);
    }

    private void initializeTablePlanOfStudy()
    {
        final ObservableList<Course> data = FXCollections.observableArrayList(
                new Course("CIS1500", "Programming", "0.5", "F17"),
                new Course("CIS2500", "Programming2", "0.5", "W17")
        );

        POSCode.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
        POSTitle.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        POSCredit.setCellValueFactory(new PropertyValueFactory<Course, String>("credit"));
        POSSemester.setCellValueFactory(new PropertyValueFactory<Course, String>("semester"));

        tablePlanOfStudy.setItems(data);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("Uncompleted", 30),
                        new PieChart.Data("Completed", 70));
        POSPieChart.setData(pieChartData);



//        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
//        PieChart.Data slice2 = new PieChart.Data("Phone"  , 67);
//        PieChart.Data slice3 = new PieChart.Data("Tablet" , 36);
//
//        POSPieChart.getData().add(slice1);
//        POSPieChart.getData().add(slice2);
//        POSPieChart.getData().add(slice3);

    }

    private void initializeProgress()
    {
        ObservableList<String> semesters = FXCollections.observableArrayList();
        semesters.add("F18");
        semesters.add("W18");
//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setCategories(semesters);
        CategoryAxis xAxis = (CategoryAxis) sctProgressMarks.getXAxis();
        xAxis.getCategories().setAll(
                "UFO sightings",
                "Paranormal Events",
                "Inexplicable Tweets"
        );
        //final NumberAxis yAxis = new NumberAxis(0, 100, 10);

    }

    public class Course
    {
        private SimpleStringProperty code;
        private SimpleStringProperty title;
        private SimpleStringProperty grade;
        private SimpleStringProperty credit;
        private SimpleStringProperty semester;
        private SimpleStringProperty status;

        Course(String code, String title, String credit, String semester)
        {
            this.code = new SimpleStringProperty(code);
            this.title = new SimpleStringProperty(title);
            this.credit = new SimpleStringProperty(credit);
            this.semester = new SimpleStringProperty(semester);
        }

        Course(String code, String title, String grade, String credit, String semester, String status)
        {
            this.code = new SimpleStringProperty(code);
            this.title = new SimpleStringProperty(title);
            this.grade = new SimpleStringProperty(grade);
            this.credit = new SimpleStringProperty(credit);
            this.semester = new SimpleStringProperty(semester);
            this.status = new SimpleStringProperty(status);
        }

        public String getCode()
        {
            return code.get();
        }
        public void setCode(String ccode)
        {
            code.set(ccode);
        }
        public String getTitle()
        {
            return title.get();
        }
        public void setTitle(String ctitle)
        {
            title.set(ctitle);
        }
        public String getGrade()
        {
            return grade.get();
        }
        public void setGrade(String cgrade)
        {
            grade.set(cgrade);
        }
        public String getCredit()
        {
            return credit.get();
        }
        public void setCredit(String ccredit)
        {
            credit.set(ccredit);
        }
        public String getSemester()
        {
            return semester.get();
        }
        public void setSemester(String csemester)
        {
            semester.set(csemester);
        }
        public String getStatus()
        {
            return status.get();
        }
        public void setStatus(String cstatus)
        {
            status.set(cstatus);
        }


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.TestClass testing = new Main.TestClass();
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
            menu1Degree.setVisible(state);
        }
        else if(menuNumber == 2)
        {
            menu2Progress.setVisible(state);
        }
        else if(menuNumber == 3)
        {
            menu3PlanOfStudy.setVisible(state);
        }
        else if(menuNumber == 4)
        {
            menu4Transcript.setVisible(state);
        }
        else if(menuNumber == 5)
        {
            menu5DegreeCourses.setVisible(state);
        }
    }
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
