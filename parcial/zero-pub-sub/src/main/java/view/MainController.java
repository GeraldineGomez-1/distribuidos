package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Application {
   
	private Stage mainStage;

	private static String UI = "SubscribeView.fxml";

	@Override
	public void start(Stage stage) throws IOException {
         
	    mainStage = new Stage();
	    
		Parent root = FXMLLoader.load(getClass().getResource(UI));
		Scene frame = new Scene(root);
		mainStage.isResizable();
		mainStage.setTitle("Artistas");
		mainStage.setScene(frame);
		mainStage.centerOnScreen();
		mainStage.show();    

	}
   
	
	public boolean setScene(String UI) throws IOException {
		
		boolean done = true;
		Parent root = FXMLLoader.load(getClass().getResource(UI));
		Scene frame = new Scene(root);
		
		mainStage.setScene(frame);
		
		return done;
		
	}


	public static void main(String[] args) {
		launch(args);
	}
}
