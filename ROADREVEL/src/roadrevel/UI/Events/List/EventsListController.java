package roadrevel.UI.Events.List;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import roadrevel.UI.Events.Ajout.AjouterEventController;
import roadrevel.database.DatabaseHandler;
import roadrevel.entities.Events.Events;
import roadrevel.entities.Events.ServicesEvents;
import roadrevel.resources.AlertMaker;


public class EventsListController implements Initializable {

    ObservableList<Events> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Events> tableView;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;
    @FXML
    private JFXTextField Search;
    @FXML
    private TableColumn<Events, String> nameCol;
    @FXML
    private TableColumn<Events, String> priceCol;
    @FXML
    private TableColumn<Events, String> posterCol;
    @FXML
    private TableColumn<Events, String > CityNameCol;
    @FXML
    private TableColumn<Events, String > DescpCol;
    @FXML
    private TableColumn<Events, String > img2Col;
    
    @FXML
    private TableColumn<Events, String > Img3Col;
    ServicesEvents sp = new ServicesEvents() ;
 private Connection cnx = DatabaseHandler.getInstance().getCnx();
    
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

        private void initCol() {
            nameCol.setCellValueFactory(new PropertyValueFactory<>("Event_name"));
            CityNameCol.setCellValueFactory(new PropertyValueFactory<>("CityName"));
            DescpCol.setCellValueFactory(new PropertyValueFactory<>("Event_description"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("EventPrice"));
            posterCol.setCellValueFactory(new PropertyValueFactory<>("EventPoster"));
            img2Col.setCellValueFactory(new PropertyValueFactory<>("Event_pic2"));
            Img3Col.setCellValueFactory(new PropertyValueFactory<>("Event_pic3"));

        }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void loadData() {
        list.clear();
        String req = "SELECT * FROM evenement";
  
                    PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new Events(result.getInt("Event_id"), result.getString("Event_name"), result.getString("CityName"), result.getString("Event_description"),  result.getInt("EventPrice"), result.getString("EventPoster"),result.getString("Event_pic2"),result.getString("Event_pic3")));
            }
    } catch (SQLException ex) {
            Logger.getLogger(AjouterEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        tableView.setItems(list);
                  // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Events> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		Search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Place -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Place.getEvent_name().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Place.getCityName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.

                                }  else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Events> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
    }
    
    private void handleEventDelete(ActionEvent event) {
        

    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    

    @FXML

    private void closeStage(ActionEvent event) {
        getStage().close();
    }

  /*  @FXML
    private void exportAsPDF(ActionEvent event) {
                List<List> printData = new ArrayList<>();
        String[] headers = {"   Place Name   ", "  City Name  ", "  Place type  ", "  Description ", "  address  ","   Ticket Price   " };
        printData.add(Arrays.asList(headers));
        for (Events place : list) {
            List<String> row = new ArrayList<>();
            row.add(place.getPlace_name());
            row.add(place.getCityname());
            row.add(place.getPlace_Type());
            row.add(place.getPlace_Description());
            row.add(place.getPlace_Address());
            row.add(String.valueOf(place.getTickets_Price()));
            printData.add(row);
        }
        Util.initPDFExprot(rootPane, contentPane, getStage(), printData);
    }*/

    @FXML
    private void exportAsPDF(ActionEvent event) {
    }

    @FXML
    private void handleEventEdit(ActionEvent event) {
        //Fetch the selected row
        Events selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("No even selected", "Please select an event to edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/roadrevel/UI/Events/Ajout/AjouterEvent.fxml"));
            Parent parent = loader.load();

            AjouterEventController controller = (AjouterEventController) loader.getController();
            controller.infalteUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit");
            stage.setScene(new Scene(parent));
            stage.show();


            stage.setOnHiding((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void handlePlaceDelete(ActionEvent event) {
        //Fetch the selected row
        Events selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("No event selected", "Please select an event for deletion.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting event");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getEvent_name()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            sp.supprimer(selectedForDeletion);

                list.remove(selectedForDeletion);
        }
    }
    }




