package Service;

import Domain.*;

public class RefereeSystem {


    private LeagueAndGameManagement leagueAndGameManagement;
    private RefereeManagement refereeManagement;
    private EventReportManagement eventReportManagement;

    public RefereeSystem(LeagueAndGameManagement leagueAndGameManagement, RefereeManagement refereeManagement, EventReportManagement eventReportManagement) {
        this.leagueAndGameManagement = leagueAndGameManagement;
        this.refereeManagement = refereeManagement;
        this.eventReportManagement = eventReportManagement;

    }

    public void addEventToGame(Referee referee, Game game, Event event){

    }

    public void makeGameReport(Game game){}

}
