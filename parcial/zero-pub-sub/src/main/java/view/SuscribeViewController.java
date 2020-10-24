package view;

import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Artist;

public class SuscribeViewController {
	
	@FXML
	private TableView<Artist> tableSuscribe;
	
	@FXML
	private TableColumn<Artist, Integer> columnId;
	
	@FXML
	private TableColumn<Artist, String> columnArtist;
	
	@FXML
	private TableColumn<Data, Void> columnActions;
	
	@FXML
	private MenuItem msgBtn;
	
	
	@FXML
	private URL location;
	
	private final ObservableList<Artist> data =
	        FXCollections.observableArrayList(
	            new Artist(1, "Jacob Smith")
             );
	
	public SuscribeViewController() {
		tableSuscribe = new TableView<Artist>();
	}
	
	
	@FXML
    private void initialize() 
    {
		columnId.setCellValueFactory(
                new PropertyValueFactory<Artist, Integer>("id"));
		
		columnArtist.setCellValueFactory(
                new PropertyValueFactory<Artist, String>("name"));
		
		tableSuscribe.setItems(data);
		
		addButtonToTable();
    }
	
	@FXML
	public void goToMesagges() {
		
	}
	
	private void addButtonToTable() {
   
        Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
            @Override
            public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
                 TableCell<Data, Void> cell = new TableCell<Data, Void>() {
                   
                    private final Button btn = new Button("Suscribirse");

                    { 
                    	btn.setFocusTraversable(false);
                      	//btn.setStyle("-fx-background-color:  #5DADE2");
                    	
                        /*btn.setOnAction((ActionEvent event) -> {
                            Data data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                        });*/
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
