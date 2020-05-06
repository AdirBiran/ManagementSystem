package Domain;

import Data.Database;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Admin implements Role {

    private LinkedList<Team> permanentlyClosedTeams;

    public Admin() {

        permanentlyClosedTeams = new LinkedList<>();
    }

    public boolean closeTeamPermanently(Team team)
    {
        if(!team.isPermanentlyClosed() && !permanentlyClosedTeams.contains(team)) {
            team.setActive(false);
            team.setPermanentlyClosed(true);
            permanentlyClosedTeams.add(team);
            //Maybe add a list of permanently closed teamsToManage to Database
            //What happens to the members of the teamsToManage???
            return true;
        }
        return false;
    }
    public User addNewPlayer(String firstName, String lastName, String mail, Date birthDate, Player.RolePlayer role, double price){
        return UserFactory.getNewPlayer(firstName, lastName, mail, birthDate, role, price);
    }
    public User addNewCoach(String firstName, String lastName, String mail, Coach.TrainingCoach training, Coach.RoleCoach role, double price){
        return UserFactory.getNewCoach(firstName, lastName, mail, training, role, price);
    }
    public User addNewTeamOwner(String firstName, String lastName, String mail){
        return  UserFactory.getNewTeamOwner(firstName, lastName, mail);
    }
    public User addNewTeamManager(String firstName, String lastName, String mail, double price,boolean manageAssets , boolean finance){
        return UserFactory.getNewTeamManager(firstName, lastName, mail, price, manageAssets, finance);
    }
    public User addNewUnionRepresentative(String firstName, String lastName, String mail){
        return UserFactory.getNewUnionRepresentative(firstName, lastName, mail);
    }

    public User addNewAdmin(String password,String firstName, String lastName, String mail){
        return UserFactory.getNewAdmin(password,firstName, lastName, mail);
    }

    public String removeUser(String userId)
    {
        User user = Database.getUser(userId);
        TeamOwner roleTeamOwner = (TeamOwner) user.checkUserRole("TeamOwner");
        if(roleTeamOwner!=null){
            List<Team> teams = roleTeamOwner.getTeamsToManage();
            if(teams!=null){
                for(Team team:teams )
                    if(team.getTeamOwners().size()==1)
                        return "The user cannot be deleted";
            }
        }
        return Database.removeUser(userId);
    }

    /**The Admin is responsible for adding
     * (including returning the Field to be active)
     * And removal of a Field*/
    public void removeField(Field field){
        if(field.getTeams().size() == 0){
            Database.removeAsset(field.getID());
        }
    }

    /**The Admin is responsible for adding
     * (including returning the Field to be active)
     * And removal of a Field*/
    public void addField(Field field){
        Database.addAsset(field);
        field.reactivate();
    }

    public void responseToComplaint(){}
    public void viewLog(){}
    public void trainModel(){}

    @Override
    public String myRole() {
        return "Admin";
    }


}
