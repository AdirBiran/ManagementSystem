package Service;

import Domain.ComplaintManager;
import Domain.EditPersonalInfo;
import Domain.Searcher;
import Presentation.FootballManagementSystem;

public class UserSystem extends GuestSystem{

    private ComplaintManager complaintManger;
    private EditPersonalInfo editPersonalInfo;
    private PersonalPageSystem personalPageSystem;

    public UserSystem(FootballManagementSystem system, Searcher searcher, ComplaintManager complaintManger,
                      EditPersonalInfo editPersonalInfo, PersonalPageSystem personalPageSystem) {
        super(system, searcher);
        this.complaintManger = complaintManger;
        this.editPersonalInfo = editPersonalInfo;
        this.personalPageSystem = personalPageSystem;
    }

    public void viewSearchHistory(){

    }
    public void logOut(){

    }
    public void viewPersonalDetails(){

    }
    public void editPersonalDetails(){

    }
}
