package Service;

import Domain.EventReportManagement;
import Domain.GameManagement;
import Domain.RefereeManagement;
import Presentation.FootballManagementSystem;

public class RefereeSystem {

    private FootballManagementSystem system;
    private GameManagement gameManagement;
    private RefereeManagement refereeManagement;
    private EventReportManagement eventReportManagement;

    public RefereeSystem(FootballManagementSystem system, GameManagement gameManagement, RefereeManagement refereeManagement, EventReportManagement eventReportManagement) {
        this.system = system;
        this.gameManagement = gameManagement;
        this.refereeManagement = refereeManagement;
        this.eventReportManagement = eventReportManagement;

    }
}
