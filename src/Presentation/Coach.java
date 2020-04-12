package Presentation;

import Domain.Asset;
import Domain.PersonalPage;
import Domain.Team;

import java.util.List;

public class Coach extends HasAPage implements Asset {

    private String training;
    private String role;
    private Team team;

    public Coach(String firstName,String lastName, String mail, PersonalPage page, String training, String role, Team team) {
        super(firstName,lastName, "C", mail, page);
        this.training = training;
        this.role = role;
        this.team = team;
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    /**
     *
     * @return
     */
    public String getTraining() {
        return training;
    }

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @return
     */
    public PersonalPage getPage() {
        return page;
    }

    /**
     *
     * @return
     */
    public Team getTeam() {
        return team;
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
