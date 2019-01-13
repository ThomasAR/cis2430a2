import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LaunchDialog extends Application {

	public LaunchDialog()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Error!");		
		alert.showAndWait();
	}

	public LaunchDialog(String input)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("");
		alert.setHeaderText(input);
		alert.showAndWait();
	}
    @Override
    public void start(Stage primaryStage) throws Exception
    {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Look, an Error Dialog");
		alert.setContentText("Ooops, there was an error!");
		
		alert.showAndWait();
	}

    public static void main(String[] args)
    {
		Application.launch(args);
	}
}