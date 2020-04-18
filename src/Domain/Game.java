package Domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Game {

    private String id;
    private Date date;
    private int hostScore;//number of goals
    private int guestScore;//number of goals
    private Field field;
    private Referee mainReferee; // exactly 1, check type of referee
    private List<Referee> sideReferees; // between 2 and 6, check type of referee
    private Team hostTeam; // check type of team
    private Team guestTeam; // check type of team
    private HashMap<Fan, ReceiveAlerts> fansForAlerts; //list of fans that signed up to receive game alerts
    private EventReport eventReport;
    private LeagueInSeason league;


    public Game(Date date, Field field, Referee mainReferee, List<Referee> sideReferees,
                Team hostTeam, Team guestTeam, LeagueInSeason league) {
        this.league = league;
        league.addGame(this);
        this.id = "G"+IdGenerator.getNewId();
        this.date = date;
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
                ", hostTeam=" + hostTeam +
                ", guestTeam=" + guestTeam +
                '}';
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    /**
     *
     * @param fan - signed up for game alerts
     * @param receiveAlerts- how to get the alerts
     * @return true- if the fan is added to list to receive game alerts
     */
    public boolean addFanForNotifications(Fan fan, ReceiveAlerts receiveAlerts) {
        if(fansForAlerts.get(fan)==null) {
            fansForAlerts.put(fan, receiveAlerts);
            return true;
        }
        return false;
    }

    public EventReport getEventReport() {
        return eventReport;
    }

    public String getId() {
        return id;
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public Date getDate() {
        return date;
    }

    /*
    number of goals
     */
    public int hostScore() {
        return hostScore;
    }
    /*
        number of goals
    */
    public int guestScore() {
        return guestScore;
    }

    public void setHostScore(int hostScore) {
        this.hostScore = hostScore;
    }

    public void setGuestScore(int guestScore) {
        this.guestScore = guestScore;
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
