package Presentation;

import Domain.Asset;
import Domain.PersonalPage;
import Domain.Team;

public class Coach extends HasAPage implements Asset {

    private String training;
    private String role;
    private PersonalPage page;
    private Team team;

    public Coach()
    {

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
