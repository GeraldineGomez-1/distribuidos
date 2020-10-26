package view;

import java.io.IOException;
import java.net.URL;

import com.pruebaps.Subscriber;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Artist;


/**
 * Controlador de la vista donde se muestran el usuario
 * puede subscribirse a los diferentes artistas 
 * @author Grupo 2
 *
 */
public class SubscribeViewController {

	@FXML
	private TableView<Artist> tableSubscribe;

	@FXML
	private TableColumn<Artist, Integer> columnId;

	@FXML
	private TableColumn<Artist, String> columnArtist;

	@FXML
	private TableColumn<Data, Void> columnActions;

	@FXML
	private MenuItem msgBtn;

	@FXML
	private Label label;

	@FXML
	private URL location;


	private ObservableList<Artist> data;
			
    
	private Subscriber suscriber;
	
	
	public SubscribeViewController() {
		tableSubscribe = new TableView<Artist>();
        suscriber = new Subscriber();
        data = FXCollections.observableArrayList(
			   new Artist(1, "Jacob Smith"), new Artist(2, "Jacob Smith")
			   );
	}
	
	@FXML
	private void initialize() 
	{
		columnId.setCellValueFactory(
				new PropertyValueFactory<Artist, Integer>("id"));

		columnArtist.setCellValueFactory(
				new PropertyValueFactory<Artist, String>("name"));
        
		data = suscriber.getData();
		tableSubscribe.setItems(data);
		addButtonToTable();
		
		
	}

	@FXML
	public void goToPosts(ActionEvent event) throws IOException {

		Stage stage;
		Parent root = FXMLLoader.load(getClass().getResource("MessageView.fxml"));
		Scene frame = new Scene(root);

		stage=(Stage) label.getScene().getWindow();
		stage.setScene(frame);

	}
    
	@FXML
	public void goToSubscribe() throws IOException {

		Stage stage;
		Parent root = FXMLLoader.load(getClass().getResource("SubscribeView.fxml"));
		Scene frame = new Scene(root);

		stage=(Stage) label.getScene().getWindow();
		stage.setScene(frame);
	}

	private void addButtonToTable() {

		Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
			@Override
			public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
				TableCell<Data, Void> cell = new TableCell<Data, Void>() {

					private Button btn = new Button("Suscribirse");

					{ 
						btn.setFocusTraversable(false);
						btn.setOnAction(new EventHandler<ActionEvent>() {
						    @Override public void handle(ActionEvent e) {   							
						    	Artist row = data.get(getIndex());
						    	//Data data = getTableView().getItems().get(getIndex());
		                        System.out.println("selectedData: " + row.getName());
						    }
						});
						
						btn.setId("btn-subscribe");

					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				cell.setStyle("-fx-alignment: CENTER");
				return cell;
			}
		};

		columnActions.setCellFactory(cellFactory);

	}

}
