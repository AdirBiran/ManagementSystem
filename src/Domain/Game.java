package Domain;

import Presentation.Fan;
import Presentation.Referee;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Game {

    private String id;
    private Date date;
    private Time time;
    private int hostScore;
    private int guestScore;
    private Field field;
    private Referee mainReferee; // exactly 1, check type of referee
    private List<Referee> sideReferees; // between 2 and 6, check type of referee
    private Team hostTeam; // check type of team
    private Team guestTeam; // check type of team
    private HashMap<Fan, Notice> fansForAlerts; //list of fans that signed up to receive game alerts
    private EventReport eventReport;

    public Game(String id, Date date, Time time, Field field, Referee mainReferee, List<Referee> sideReferees,
                Team hostTeam, Team guestTeam) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.field = field;
        this.mainReferee = mainReferee;
        if(sideReferees==null||sideReferees.size()<2)
            throw new RuntimeException("not enough side referees");
        this.sideReferees = sideReferees;
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
        eventReport = new EventReport(this);
        hostScore=0;
        guestScore=0;
        fansForAlerts = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", hostTeam=" + hostTeam +
                ", guestTeam=" + guestTeam +
                '}';
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    /**
     *
     * @param fan - signed up for game alerts
     * @param notice- how to get the alerts
     * @return true- if the fan is added to list to receive game alerts
     */
    public void addFanForNotifications(Fan fan, Notice notice) {
        if(fansForAlerts.get(fan)==null)
            fansForAlerts.put(fan, notice);
    }

    public String getId() {
        return id;
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
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
