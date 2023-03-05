
package roadrevel.entities.Reports;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import roadrevel.database.DatabaseHandler;
import roadrevel.resources.IService;

/**
 *
 * @author Nasr
 */
public class ServiceReports implements IService<Reports>{

    private Connection cnx = DatabaseHandler.getInstance().getCnx();

    @Override
    public void ajouter(Reports r) {
        String req = "INSERT INTO reports( Report_Subject , Report_Description , Involvment , Incident_type, Incident_date , Incident_Location , Id_User ) VALUES( ? ,? ,? ,? ,? ,? ,?);";
        try {
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, r.getReport_Subject());
            pst.setString(2, r.getReport_Description());
            pst.setString(3, r.getInvolvment());
            pst.setString(4, r.getIncident_type());
            pst.setDate(5, r.getIncident_date());
            pst.setString(6, r.getIncident_location());
            pst.setInt(7, r.getId_User());
            pst.executeUpdate();
            System.out.println("Report sent  !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reports r) {
        String req = "UPDATE  reports SET Report_Subject=?, Report_Description= ? , Involvment= ? , Incident_type= ? , Incident_date=? , Incident_Location= ? , Id_User= ? WHERE Report_Id =?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, r.getReport_Subject());
            pst.setString(2, r.getReport_Description());
            pst.setString(3, r.getInvolvment());
            pst.setString(4, r.getIncident_type());
            pst.setDate(5, r.getIncident_date());
            pst.setString(6, r.getIncident_location());
            pst.setInt(7, r.getId_User());
            pst.setInt(8, r.getReport_id());
            pst.executeUpdate();
            System.out.println("report modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Reports r) {
        String req = "DELETE FROM reports WHERE Report_Id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(5, r.getReport_id());
            pst.executeUpdate();
            System.out.println("report supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reports> afficher() {
        List<Reports> list = new ArrayList<>();
        
        String req = "SELECT * FROM reports";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new Reports(result.getInt("Report_Id"), result.getString("Report_Subject"), result.getString("Report_Description"),result.getString("Involvment"), result.getString("Incident_type"), result.getDate("Incident_date"), result.getString("Incident_Location"), result.getInt("Id_User")));
            }
            System.out.println("reports récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}

