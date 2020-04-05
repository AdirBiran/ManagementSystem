package Presentation;

import Domain.Asset;
import Domain.PersonalPage;
import Domain.Team;
import java.util.Date;
import java.util.List;

public class Player extends HasAPage implements Asset {

    private Date birthDate;
    private String role;
    private PersonalPage page;
    private List<Team> teams; // at least one

    public Player()
    {

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
}
