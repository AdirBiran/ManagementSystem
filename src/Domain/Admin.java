package Domain;

import Data.Database;

public class Admin extends User {

    private Database database;
    public Admin(String firstName, String lastName, String mail, Database database) {

        super(firstName,lastName, "A", mail);
        this.database = database;
    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++
    /*
    Permanently close a team
     */
    public boolean permanentlyCloseTeam(Team team) {
        if(!team.isPermanentlyClosed()) {
            if (team.isActive())
            {
                team.setActive(false);
                team.setPermanentlyClosed(true);
                //Maybe add a list of permanently closed teams to Database
                //What happens to the members of the teams???
                return true;
            }
        }
        return false;
    }


    /**
     *
     * @param complaint
     */
    public void responseToComplaint(Complaint complaint)
    {

    }

    /**
     *
     */
    public void viewLog()
    {

    }

    /**
     *
     */
    public void trainModel()
    {

    }
    /*
       this function adds a new user to the system
    */
    public void addUser(String password, User user) {
        database.addUser(password, user);
    }
    /*
    Remove user
     */
    public String removeUser(String userId){
        return database.removeUser(userId);
    }

    // ++++++++++++++++++++++++++++ getter&setter ++++++++++++++++++++++++++++

}
