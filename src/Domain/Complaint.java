package Domain;

import Presentation.Fan;

import java.sql.Time;
import java.util.Date;

public class Complaint {

    private Date date;//also shows time
    //private Time time;
    private String description;
    private boolean isActive;
    private Fan fanComplained;

    public Complaint(Date date, String description, Fan fanComplained) {
        this.date = date;
        //this.time = time;
        this.description = description;
        this.fanComplained = fanComplained;
        isActive = true;
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public Date getDate() {
        return date;
    }


    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     *
     * @return
     */
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
