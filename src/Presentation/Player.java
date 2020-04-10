package Presentation;

import Domain.Asset;
import Domain.PersonalPage;
import Domain.Team;
import java.util.Date;
import java.util.List;

public class Player extends HasAPage implements Asset {

    private Date birthDate;
    private String role;
    private List<Team> teams; // at least one

    public Player(String firstName,String lastName, String ID, String mail, PersonalPage page, Date birthDate, String role, List<Team> teams) {
        super(firstName,lastName, ID, mail, page);
        this.birthDate = birthDate;
        this.role = role;
        //if(teams==null||teams.size()<1)
          //  throw new RuntimeException("not enough teams for the player");
        this.teams = teams;
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
}
