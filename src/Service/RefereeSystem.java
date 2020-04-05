package Service;

import Domain.EventReportManagement;
import Domain.GameManagemnet;
import Domain.RefereeManagement;
import Presentation.FootballManagementSystem;

public class RefereeSystem {

    private FootballManagementSystem system;
    private GameManagemnet gameManagemnet;
    private RefereeManagement refereeManagement;
    private EventReportManagement eventReportManagement;

    public RefereeSystem(FootballManagementSystem system, GameManagemnet gameManagemnet, RefereeManagement refereeManagement, EventReportManagement eventReportManagement) {
        this.system = system;
        this.gameManagemnet = gameManagemnet;
        this.refereeManagement = refereeManagement;
        this.eventReportManagement = eventReportManagement;

    }
}
