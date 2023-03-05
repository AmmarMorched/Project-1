/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roadrevel.entities.Events;
import roadrevel.entities.Events.Events;
import roadrevel.database.DatabaseHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import roadrevel.resources.IService;

/**
 *
 * @author GAMING A15
 */
public class ServicesEvents implements IService<Events> {
    
    
    private Connection cnx = DatabaseHandler.getInstance().getCnx();
    
    @Override
    public void ajouter(Events e) {
        String req = "INSERT INTO evenement(Event_name, CityName, Event_description, EventPrice, EventPoster, Event_pic2, Event_pic3) VALUES(?, ?, ?, ?, ?, ?, ?);";
        try {
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, e.getEvent_name());
            pst.setString(2, e.getCityName());
            pst.setString(3, e.getEvent_description());
            pst.setInt(4, e.getEventPrice());
            pst.setString(5, e.getEventPoster());
            pst.setString(6, e.getEvent_pic2());
            pst.setString(7, e.getEvent_pic3());
            
            
            pst.executeUpdate();
            System.out.println("event ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifier(Events e) {
        String req = "UPDATE  reports SET Event_name=?, CityName= ? , Event_description= ? , EventPrice= ? , EventPoster=? , Event_pic2= ? , Event_pic3= ? WHERE Event_id =?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, e.getEvent_name());
            pst.setString(2, e.getCityName());
            pst.setString(3, e.getEvent_description());
            pst.setInt(4, e.getEventPrice());
            pst.setString(5, e.getEventPoster());
            pst.setString(6, e.getEvent_pic2());
            pst.setString(7, e.getEvent_pic3());
            pst.setInt(8, e.getEvent_id());
            
            pst.executeUpdate();
            System.out.println("event modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override
    public void supprimer(Events e) {
        String req = "DELETE FROM evenement WHERE Event_id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            System.out.println("");
            pst.setInt(1, e.getEvent_id());
            pst.executeUpdate();
            System.out.println("event supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Events> afficher() {
        List<Events> list = new ArrayList<>();
        
        String req = "SELECT * FROM evenement";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new Events(result.getInt("Event_id"), result.getString("Event_name"), result.getString("CityName"), result.getString("Event_description"),  result.getInt("EventPrice"), result.getString("EventPoster"),result.getString("Event_pic2"),result.getString("Event_pic3")));
            }
            System.out.println("event récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    

          /*public ObservableList<Events> searchent2(String searchTerm) {
        ObservableList<Events> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM evenement");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Events offre = new Events(
                          rs.getString(2), 
                          rs.getString(3),
                          rs.getString(5)
                );
                list.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    
    
    }*/
          
          

    
}
