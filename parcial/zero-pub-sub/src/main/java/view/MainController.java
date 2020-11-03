package view;

import java.io.IOException;

import com.pruebaps.Subscriber;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Artist;

/**
 * Controlador principal de las vistas, es donde
 * inicia la aplicación
 * @author Grupo 2
 *
 */
public class MainController {

	private Stage mainStage;

	private static String UI = "SubscribeView.fxml";
	
	private SubscribeViewController scc;
    	
	public void start() throws IOException {

		mainStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(UI));
		Parent root = (Parent)loader.load();
		Scene frame = new Scene(root);
		//scc = (SubscribeViewController)loader.getController();
		//scc.setDataTable(data);
		
		mainStage.isResizable();
		mainStage.setTitle("Artistas");
		mainStage.setScene(frame);
		mainStage.centerOnScreen();
		mainStage.show();    

	}
	

	public Stage getMainStage() {
		return mainStage;
	}


	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
	
	public SubscribeViewController scc () {
		return scc;
	}


}
