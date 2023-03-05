/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roadrevel.entities.Reports;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Nasr
 */
public class Reports {
    private int Report_id ;
    private String Report_Subject ;
    private String Report_Description ;
    private String Involvment ;
    private String Incident_type ; 
    private Date Incident_date ; 
    private String Incident_location ;
    private int Id_User ; 

    public Reports(int Report_id, String Report_Subject, String Report_Description, String Involvment, String Incident_type, Date Incident_date, String Incident_location, int id_User) {
        this.Report_id = Report_id;
        this.Report_Subject = Report_Subject;
        this.Report_Description = Report_Description;
        this.Involvment = Involvment;
        this.Incident_type = Incident_type;
        this.Incident_date = Incident_date;
        this.Incident_location = Incident_location;
        this.Id_User = Id_User;
    }

    public Reports(int Report_id) {
        this.Report_id = Report_id;
    }

    public Reports(int Report_id, String Report_Subject, String Report_Description, String Involvment, String Incident_location) {
        this.Report_id = Report_id;
        this.Report_Subject = Report_Subject;
        this.Report_Description = Report_Description;
        this.Involvment = Involvment;
        this.Incident_location = Incident_location;
    }

    
    

    public Reports(String Report_Subject, String Report_Description, String Involvment, String Incident_type, Date Incident_date, String Incident_location, int Id_User) {
        this.Report_Subject = Report_Subject;
        this.Report_Description = Report_Description;
        this.Involvment = Involvment;
        this.Incident_type = Incident_type;
        this.Incident_date = Incident_date;
        this.Incident_location = Incident_location;
        this.Id_User = Id_User;
    }

    public Reports(String Report_Subject, String Report_Description, String Involvment, String Incident_type, Date Incident_date, String Incident_location) {
        this.Report_Subject = Report_Subject;
        this.Report_Description = Report_Description;
        this.Involvment = Involvment;
        this.Incident_type = Incident_type;
        this.Incident_date = Incident_date;
        this.Incident_location = Incident_location;
    }

    public Reports(int Report_id, String text, String text0, String text1, DatePicker DatePicker, String value, int Id_User) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    

    

    

    

    

    public int getReport_id() {
        return Report_id;
    }

    public void setReport_id(int Report_id) {
        this.Report_id = Report_id;
    }

    public String getReport_Subject() {
        return Report_Subject;
    }

    public void setReport_Subject(String Report_Subject) {
        this.Report_Subject = Report_Subject;
    }

    public String getReport_Description() {
        return Report_Description;
    }

    public void setReport_Description(String Report_Description) {
        this.Report_Description = Report_Description;
    }

    public String getInvolvment() {
        return Involvment;
    }

    public void setInvolvment(String Involvment) {
        this.Involvment = Involvment;
    }

    public String getIncident_type() {
        return Incident_type;
    }

    public void setIncident_type(String Incident_type) {
        this.Incident_type = Incident_type;
    }

    public Date getIncident_date() {
        return Incident_date;
    }

    public void setIncident_date(Date Incident_date) {
        this.Incident_date = Incident_date;
    }

    public String getIncident_location() {
        return Incident_location;
    }

    public void setIncident_location(String Incident_location) {
        this.Incident_location = Incident_location;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    @Override
    public String toString() {
        return "Reports{" + "Report_id=" + Report_id + ", Report_Subject=" + Report_Subject + ", Report_Description=" + Report_Description + ", Involvment=" + Involvment + ", Incident_type=" + Incident_type + ", Incident_date=" + Incident_date + ", Incident_location=" + Incident_location + ", Id_User=" + Id_User + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reports other = (Reports) obj;
        if (this.Report_id != other.Report_id) {
            return false;
        }
        if (!Objects.equals(this.Report_Subject, other.Report_Subject)) {
            return false;
        }
        if (!Objects.equals(this.Report_Description, other.Report_Description)) {
            return false;
        }
        if (!Objects.equals(this.Involvment, other.Involvment)) {
            return false;
        }
        if (!Objects.equals(this.Incident_type, other.Incident_type)) {
            return false;
        }
        if (!Objects.equals(this.Incident_location, other.Incident_location)) {
            return false;
        }
        if (!Objects.equals(this.Id_User, other.Id_User)) {
            return false;
        }
        return Objects.equals(this.Incident_date, other.Incident_date);
    }
    
}
