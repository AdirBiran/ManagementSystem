package Domain;

import Presentation.Fan;

import java.sql.Time;
import java.util.Date;

public class Complaint {

    private Date date;
    private Time time;
    private String description;
    private boolean isActive;
    private Fan fanComplained;

    public Complaint()
    {

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
    public Time getTime() {
        return time;
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
    public boolean isActive() {
        return isActive;
    }

    /**
     *
     * @return
     */
    public Fan getFanComplained() {
        return fanComplained;
    }
}
