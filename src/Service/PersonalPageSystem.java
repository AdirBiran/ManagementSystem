package Service;

import Domain.PersonalPageManagement;
import Presentation.FootballManagementSystem;

public class PersonalPageSystem {
    private PersonalPageManagement personalPageManagement;
    private FootballManagementSystem system;

    public PersonalPageSystem(PersonalPageManagement personalPageManagement, FootballManagementSystem system) {
        this.personalPageManagement = personalPageManagement;
        this.system = system;
    }
}
