package Presentation;

import Domain.Asset;
import Domain.PersonalPage;
import Domain.Team;

import java.util.List;

public class Coach extends HasAPage implements Asset {

    private String training;
    private String role;
    private Team team;

    public Coach(String firstName,String lastName, String mail, PersonalPage page, String training, String role) {
        super(firstName,lastName, "C", mail, page);
        this.training = training;
        this.role = role;
        this.team = null;
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

    public void addTeam(Team team){
        this.team = team;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
