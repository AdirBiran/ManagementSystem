package Domain;

import Presentation.Fan;
import Presentation.Referee;
import Service.NotificationSystem;

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
    private List<Fan> fansForAlerts; //list of fans that signed up to receive game alerts
    private NotificationSystem notifications;
    private EventReport eventReport;

    public Game()
    {

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    /**
     *
     * @param fan - signed up for game alerts
     * @return true- if the fan is added to list to receive game alerts
     */
    public boolean addFanForNotifications(Fan fan) {
        if(!fansForAlerts.contains(fan)) {
            fansForAlerts.add(fan);
            return true;
        }
        return false;
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        notifications.refereeAlertsChangeDate(this,date);

    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
        notifications.refereeAlertsChangeDate(this, time);
    }

    public int hostScore() {
        return hostScore;
    }

    public int guestScore() {
        return guestScore;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
        notifications.refereeAlertsChangeGameLocation(this, field);
    }

    public List<Event> getEvents() {
        return events;
    }

    public Referee getMainReferee() {
        return mainReferee;
    }

    public List<Referee> getSideReferees() {
        return sideReferees;
    }

    public Team getHostTeam() {
        return hostTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }





}
