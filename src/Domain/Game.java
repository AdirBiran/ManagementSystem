package Domain;

import java.util.*;

public class Game extends Observable {

    private String id;
    private String name;
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
        addRefereeToObservers(mainReferee, sideReferees);
        this.hostTeam = hostTeam;
        this.guestTeam = guestTeam;
        this.name = hostTeam.getName() + " VS "+ guestTeam.getName();
        eventReport = new EventReport();
        hostScore=0;
        guestScore=0;
        fansForAlerts = new HashMap<>();
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    @Override
    public String toString() {
        return id +","+name +","+date;

    }

    private void addRefereeToObservers(Referee mainReferee, List<Referee> sideReferees) {
        this.addObserver(mainReferee);
        this.addObserver(sideReferees.get(0));
        this.addObserver(sideReferees.get(1));
    }

    /**
     * @param fan - signed up for game alerts
     * @param receiveAlerts- how to get the alerts
     * @return true- if the fan is added to list to receive game alerts
     */
    public boolean addFanForNotifications(Fan fan, ReceiveAlerts receiveAlerts) {
        if(fansForAlerts.get(fan)==null) {
            fansForAlerts.put(fan, receiveAlerts);
            this.addObserver(fan);
            return true;
        }
        return false;
    }

    public List<String> getEventReportString() {
        List<String> events = new LinkedList<>();
        for(Event event : eventReport.getEvents())
            events.add(event.toString());
        return events;
    }

    public void setNews(String news) {
        setChanged();
        notifyObservers(news);
    }
    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++
    public String getId() {
        return id;
    }

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

    public String getName() {
        return name;
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

    public EventReport getEventReport() {
        return eventReport;
    }

    public void setDate(Date newDate) {
        this.date = newDate;
        setNews("Date of the game between the teams: " +this.name+ " change to "+this.date); // referees and fans
    }

    public void setField(Field newField) {
        this.field = newField;
        setNews("Location of the game between the teams: " +this.name+ " change to "+this.field.getName()); // referees and fans
    }

    public void setNewsFromReferee(Object news){
        setChanged();
        notifyObservers(news);
    }

    public LeagueInSeason getLeague() {
        return league;
    }

    public HashMap<Fan, ReceiveAlerts> getFansForAlerts() {
        return fansForAlerts;
    }
}
