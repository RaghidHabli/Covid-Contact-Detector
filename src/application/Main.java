package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		
			Pane root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
			Scene scene = new Scene(root);
		     scene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

