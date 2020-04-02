package Domain;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Game {

    private Date date;
    private Time time;
    private int hostScore;
    private int guestScore;
    private Field field;
    private List<Event> events;
    private Referee mainReferee; // exactly 1, check type of referee
    private List<Referee> sideReferees; // between 2 and 6, check type of referee
    private Team hostTeam; // check type of team
    private Team guestTeam; // check type of team

    public Game()
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
    public int hostScore() {
        return hostScore;
    }

    /**
     *
     * @return
     */
    public int guestScore() {
        return guestScore;
    }

    /**
     *
     * @return
     */
    public Field getField() {
        return field;
    }

    /**
     *
     * @return
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     *
     * @return
     */
    public Referee getMainReferee() {
        return mainReferee;
    }

    /**
     *
     * @return
     */
    public List<Referee> getSideReferees() {
        return sideReferees;
    }

    /**
     *
     * @return
     */
    public Team getHostTeam() {
        return hostTeam;
    }

    /**
     *
     * @return
     */
    public Team getGuestTeam() {
        return guestTeam;
    }
}
