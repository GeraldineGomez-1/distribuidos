package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Artist;

public class Home extends Application {

	
	

	private static String UI = "Proof.fxml";
	
	@Override
	public void start(Stage stage) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource(UI));
		Scene frame = new Scene(root);
		stage.isResizable();
		stage.setTitle("Suscribe");
		stage.setScene(frame);
		stage.centerOnScreen();
		stage.show();    
		
		

	}
	
	 public void pressButton(ActionEvent event){               
		    try {
		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sedondFXML.fxml"));
		            Parent root = (Parent) fxmlLoader.load();
		            Stage stage = new Stage();
		            stage.setScene(new Scene(root));  
		            stage.show();
		            //main.stg.close();
		    } catch(Exception e) {
		       e.printStackTrace();
		      }
		 }
	
	 

	
	public static void main(String[] args) {
		launch(args);
	}
}