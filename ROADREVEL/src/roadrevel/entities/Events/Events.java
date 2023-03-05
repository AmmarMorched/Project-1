/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package roadrevel.entities.Events;

import java.util.Objects;

/**
 *
 * @author GAMING A15
 */
public class Events {

    private int Event_id;
    private String Event_name;
    private String Event_description;
    private String CityName;
    private int EventPrice; 
    private String EventPoster;
    private String Event_pic2;
    private String Event_pic3;

    public Events() {
    }

    public Events(int Event_id, String Event_name, String Event_description, String CityName, int EventPrice, String EventPoster, String Event_pic2, String Event_pic3) {
        this.Event_id = Event_id;
        this.Event_name = Event_name;
        this.Event_description = Event_description;
        this.CityName = CityName;
        this.EventPrice = EventPrice;
        this.EventPoster = EventPoster;
        this.Event_pic2 = Event_pic2;
        this.Event_pic3 = Event_pic3;
    }

    public Events(String Event_name, String Event_description, String CityName, int EventPrice, String EventPoster, String Event_pic2, String Event_pic3) {
        this.Event_name = Event_name;
        this.Event_description = Event_description;
        this.CityName = CityName;
        this.EventPrice = EventPrice;
        this.EventPoster = EventPoster;
        this.Event_pic2 = Event_pic2;
        this.Event_pic3 = Event_pic3;
    }

    

    public int getEvent_id() {
        return Event_id;
    }

    public void setEvent_id(int Event_id) {
        this.Event_id = Event_id;
    }

    public String getEvent_name() {
        return Event_name;
    }

    public void setEvent_name(String Event_name) {
        this.Event_name = Event_name;
    }

    public String getEvent_description() {
        return Event_description;
    }

    public void setEvent_description(String Event_description) {
        this.Event_description = Event_description;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getEventPoster() {
        return EventPoster;
    }

    public void setEventPoster(String EventPoster) {
        this.EventPoster = EventPoster;
    }

    public int getEventPrice() {
        return EventPrice;
    }

    public void setEventPrice(int EventPrice) {
        this.EventPrice = EventPrice;
    }

    

    
    

    public String getEvent_pic2() {
        return Event_pic2;
    }

    public void setEvent_pic2(String Event_pic2) {
        this.Event_pic2 = Event_pic2;
    }

    public String getEvent_pic3() {
        return Event_pic3;
    }

    public void setEvent_pic3(String Event_pic3) {
        this.Event_pic3 = Event_pic3;
    }

    

   
   @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Events other = (Events) obj;
        if (this.Event_id != other.Event_id) {
            return false;
        }
        if (!Objects.equals(this.Event_name, other.Event_name)) {
            return false;
        }
        if (!Objects.equals(this.Event_description, other.Event_description)) {
            return false;
        }
        if (!Objects.equals(this.CityName, other.CityName)) {
            return false;
        }
        if (!Objects.equals(this.EventPoster, other.EventPoster)) {
            return false;
        }
        if (!Objects.equals(this.Event_pic2, other.Event_pic2)) {
            return false;
        }
        if (!Objects.equals(this.Event_pic3, other.Event_pic3)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Events{" + "Event_id=" + Event_id + ", Event_name=" + Event_name + ", Event_description=" + Event_description + ", CityName=" + CityName + ", EventPoster=" + EventPoster + ", Event_pic2=" + Event_pic2 + ", Event_pic3=" + Event_pic3 + '}';
    }

    
   

    

    

}


