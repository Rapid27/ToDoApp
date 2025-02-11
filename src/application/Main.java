package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Parent root = FXMLLoader.load(getClass().getResource("/Views/DetailsMainView.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/Views/loginView.fxml"));
			
			primaryStage.setScene(new Scene(root , 700 , 400));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
