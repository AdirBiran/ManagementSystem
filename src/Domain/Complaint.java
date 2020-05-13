package Domain;

import java.util.Date;
import java.util.Observable;

public class Complaint extends Observable {

    private String id;
    private Date date;//also shows time
    private String description;
    private boolean isActive;
    private Fan fanComplained;
    private String response;

    public Complaint(String description, Fan fanComplained) {
        this.id = "Complaint"+IdGenerator.getNewId();
        this.date = new Date();
        this.description = description;
        this.fanComplained = fanComplained;
        this.addObserver(fanComplained);
        isActive = true;
        this.response = "";
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public Fan getFanComplained() {
        return fanComplained;
    }

    public void setResponse(String response) {
        this.response = response;
        this.isActive = false;
        setChanged();
        notifyObservers("Response to your complaint from the date"+this.date+": "+response);
    }

    /**
     * this function turns a complaint not active
     */
    public void deactivate(){
        isActive = false;
    }

    public String getId() {
        return id;
    }
}
