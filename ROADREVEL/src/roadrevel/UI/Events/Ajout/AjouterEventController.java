package roadrevel.UI.Events.Ajout;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import roadrevel.entities.Events.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import roadrevel.resources.AlertMaker;
import roadrevel.resources.Util;

/**
 * FXML Controller class
 *
 * @author GAMING A15
 */
public class AjouterEventController implements Initializable {

    ServicesEvents sp = new ServicesEvents();
    private Boolean isInEditMode = Boolean.FALSE;
    int Event_id;
    String res, res2, res3;

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    //private JFXTextField Place_Name;

    //private JFXTextArea Place_Descrp;
    //private JFXTextField Ticket_Price;
    @FXML
    private JFXButton img1;
    @FXML
    private JFXButton img2;
    @FXML
    private JFXButton img3;
    @FXML
    private JFXTextField Event_Name;
    @FXML
    private JFXTextArea Event_Descrp;
    @FXML
    private JFXTextField Event_Price;
    @FXML
    private JFXTextField CityName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void choosefile(ActionEvent event) {
        Util u = new Util();
        res = u.ImgPicker();

    }

    @FXML
    private void choosefile2(ActionEvent event) {
        Util u = new Util();
        res2 = u.ImgPicker();
    }

    @FXML
    private void choosefile3(ActionEvent event) {
        Util u = new Util();
        res3 = u.ImgPicker();
    }

    @FXML
    private void LoadSignIn(ActionEvent event) {

        int tp;

        String pn = Event_Name.getText();
        String cn = CityName.getText();
        String pt = Event_Descrp.getText();
        String pd = Event_Price.getText();

        EventHandler<MouseEvent> Cfile = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Util u = new Util();
                res = u.ImgPicker();
            }
        };
        img1.setOnMouseClicked(Cfile);
        String pi1 = res;
        String pi2 = res2;
        String pi3 = res3;
        if (pn.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter Event Name.");
            return;
        }
        if (cn.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter City where the Event is.");
            return;
        }
        if (pt.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter Event Description.");
            return;
        }

        if (Event_Price.getText().isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter the Event Price.");
            return;
        } else if (!(Event_Price.getText().matches("[0-9]+"))) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Event Price Must Be a Number");
            return;
        } else {
            tp = Integer.parseInt(Event_Price.getText());
        }
        if (pi1 == null) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter the poster.");
            return;
        }
        if (pi2 == null) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter a Second Image");
            return;
        }
        if (pi3 == null) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter a Third Image");
            return;
        }
        if (isInEditMode) {
            handleEditOperation();
            return;
        }
        Events pv = new Events(pn, cn, pt, Integer.parseInt(pd), pi1, pi2, pi3);
        sp.ajouter(pv);

    }

    public void inflateUI(Events e) {
        Event_Name.setText(e.getEvent_name());
        CityName.setText(e.getCityName());
        Event_Descrp.setText(e.getEvent_description());

        Event_Price.setText(String.valueOf(e.getEventPrice()));

        Event_id = e.getEvent_id();

        isInEditMode = Boolean.TRUE;
    }

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }

    @FXML
    private void LoadCancel(ActionEvent event) {
        getStage().close();
    }

    private void handleEditOperation() {
        Events e = new Events(Event_id, Event_Name.getText(), Event_Descrp.getText(), CityName.getText(), Integer.parseInt(Event_Price.getText()), res, res2, res3);
        sp.modifier(e);
        AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Success", "Update complete");

    }

    public void infalteUI(Events e) {
        Event_Name.setText(e.getEvent_name());
        CityName.setText(e.getCityName());
        Event_Descrp.setText(e.getEvent_description());

        Event_Price.setText(String.valueOf(e.getEventPrice()));

        Event_id = e.getEvent_id();

        isInEditMode = Boolean.TRUE;
    }

}
