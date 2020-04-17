package Service;

import Domain.EventReportManagement;
import Domain.LeagueAndGameManagement;
import Domain.RefereeManagement;

public class RefereeSystem {


    private LeagueAndGameManagement leagueAndGameManagement;
    private RefereeManagement refereeManagement;
    private EventReportManagement eventReportManagement;

    public RefereeSystem(LeagueAndGameManagement leagueAndGameManagement, RefereeManagement refereeManagement, EventReportManagement eventReportManagement) {
        this.leagueAndGameManagement = leagueAndGameManagement;
        this.refereeManagement = refereeManagement;
        this.eventReportManagement = eventReportManagement;

    }

}
