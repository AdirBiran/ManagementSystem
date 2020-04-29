package Domain;

import java.util.Date;

public class Complaint {

    private Date date;//also shows time
    private String description;
    private boolean isActive;
    private Fan fanComplained;

    public Complaint(String description, Fan fanComplained) {
        this.date = new Date();
        this.description = description;
        this.fanComplained = fanComplained;
        isActive = true;
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

    /**
     * this function turns a complaint not active
     */
    public void deactivate(){
        isActive = false;
    }
}
