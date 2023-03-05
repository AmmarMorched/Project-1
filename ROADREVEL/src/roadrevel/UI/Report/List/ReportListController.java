package roadrevel.UI.Report.List;

import roadrevel.UI.Events.List.*;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import roadrevel.UI.Report.Ajouter.AjouterReportController;
import roadrevel.database.DatabaseHandler;
import roadrevel.entities.Reports.Reports;
import roadrevel.entities.Reports.ServiceReports;
import roadrevel.resources.AlertMaker;


public class ReportListController implements Initializable {

    ObservableList<Reports> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Reports> tableView;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane contentPane;
    
    ServiceReports sp = new ServiceReports() ;
 private Connection cnx = DatabaseHandler.getInstance().getCnx();
    
    @FXML
    private JFXTextField Search;
    @FXML
    private TableColumn<Reports, String> colSubject;
    @FXML
    private TableColumn<Reports, String> colDescrip;
    @FXML
    private TableColumn<Reports, String> colInvolv;
    @FXML
    private TableColumn<Reports, String> colType;
    @FXML
    private TableColumn<Reports, Date> colDate;
    @FXML
    private TableColumn<Reports, String> colLocation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
    }

        private void initCol() {
            colSubject.setCellValueFactory(new PropertyValueFactory<>("Report_Subject"));
            colDescrip.setCellValueFactory(new PropertyValueFactory<>("Report_Description"));
            
            colInvolv.setCellValueFactory(new PropertyValueFactory<>("Involvment"));
            
            colType.setCellValueFactory(new PropertyValueFactory<>("Incident_type"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("Incident_date"));
            colLocation.setCellValueFactory(new PropertyValueFactory<>("Incident_location"));
            

        }

    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }

    private void loadData() {
        list.clear();
        String req = "SELECT * FROM Reports";
  
                    PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new Reports(result.getInt("Report_id"), result.getString("Report_Subject"), result.getString("Report_Description"), result.getString("Involvment"),  result.getString("Incident_type"), result.getDate("Incident_date"),result.getString("Incident_location"),result.getInt("Id_User")));
            }
    } catch (SQLException ex) {
            Logger.getLogger(AjouterReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        tableView.setItems(list);
                  // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Reports> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		Search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Place -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Place.getReport_Subject().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Place.getReport_Description().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.

                                }  else  if (Place.getInvolvment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.

                                }  else  if (Place.getIncident_type().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                
                                }else if (Place.getIncident_location().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                }else
                                    return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reports> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
    }
    
    @FXML
    private void handleReportDelete(ActionEvent event) {
        //Fetch the selected row
        Reports selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            AlertMaker.showErrorMessage("No report selected", "Please select a report for deletion.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting report");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getReport_Subject()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            sp.supprimer(selectedForDeletion);

                list.remove(selectedForDeletion);

    }}

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void handleReportEdit(ActionEvent event) {
        //Fetch the selected row
        Reports selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            AlertMaker.showErrorMessage("No report selected", "Please select a report for edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/roadrevel/UI/Report/Ajouter/AjouterReport.fxml"));
            Parent parent = loader.load();

            AjouterReportController controller = (AjouterReportController) loader.getController();
            controller.infalteUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit report");
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
    }




