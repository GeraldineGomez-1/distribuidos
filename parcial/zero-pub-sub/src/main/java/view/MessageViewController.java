package view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.pruebaps.SubscriberSingleton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Crick;

/**
 * Controlador de la vista donde se muestran
 * los mensajes del publicador 
 * @author Grupo 2
 *
 */
public class MessageViewController {

	@FXML
	private MenuItem subscriptionsBtn;
	
	@FXML
	private TableView<Crick> tablePosts;
	
	@FXML
	private TableColumn<Crick, Integer> columnId;
	
	@FXML
	private TableColumn<Crick, LocalDate> columnDate;
	
	@FXML
	private TableColumn<Crick, LocalTime> columnTime;
	
	@FXML
	private TableColumn<Crick, String> columnArtist;
	
	@FXML
	private TableColumn<Crick, String> columnMessage;
	
	@FXML 
	private Label label;
	
		

	public MessageViewController() {
		 tablePosts = new TableView<Crick>();
		 		 
	}
	
	@FXML
    private void initialize() throws Exception 
    {
				
		columnDate.setCellValueFactory(
                new PropertyValueFactory<Crick, LocalDate>("datePublishing"));
		
		columnTime.setCellValueFactory(
                new PropertyValueFactory<Crick, LocalTime>("timePublishing"));
		
		columnMessage.setCellValueFactory(
                new PropertyValueFactory<Crick, String>("message"));
		
		columnArtist.setCellValueFactory(
                new PropertyValueFactory<Crick, String>("artistName"));

		tablePosts.setItems(SubscriberSingleton.getInstance().getSubscriber().getCricks());	    
		
    }
	
	@FXML
	public void goToSubscriptions() throws IOException {
	   
		Stage stage;
		Parent root = FXMLLoader.load(getClass().getResource("SubscribeView.fxml"));
		Scene frame = new Scene(root);
		
		stage=(Stage) label.getScene().getWindow();
	    stage.setScene(frame);
	  	    
	}
	
	@FXML
	public void goToPosts() throws IOException {
		
		Stage stage;
		Parent root = FXMLLoader.load(getClass().getResource("MessageView.fxml"));
		Scene frame = new Scene(root);
		
		stage=(Stage) label.getScene().getWindow();
	    stage.setScene(frame);
	}
	
}
