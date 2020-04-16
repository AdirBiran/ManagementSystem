package Presentation;

import Domain.Asset;
import Domain.PersonalPage;
import Domain.Team;
import java.util.Date;
import java.util.List;
import java.util.LinkedList;

public class Player extends HasAPage implements Asset {

    private Date birthDate;
    private String role;
    private List<Team> teams; // at least one

    public Player(String firstName,String lastName, String mail, PersonalPage page, Date birthDate, String role) {
        super(firstName,lastName, "P", mail, page);
        this.birthDate = birthDate;
        this.role = role;
        this.teams = new LinkedList<>();
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public Date getBirthDate() {
        return birthDate;
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
    public List<Team> getTeams() {
        return teams;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    public boolean addTeam(Team team){
        if(teams.contains(team)) return false;
        teams.add(team);
        return true;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
