package application;

import application.utils.LogService;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			MainSceneController controller = new MainSceneController();
			loader.setController(controller);
			primaryStage.setResizable(false);
			primaryStage.setTitle("CAN Simulation Tool for Android Based IVI Systems");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("res/icon.png")));
			primaryStage.setScene(new Scene(root));
			LogService.dLog("Starting Application");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
