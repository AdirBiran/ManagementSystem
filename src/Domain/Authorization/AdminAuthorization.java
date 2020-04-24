package Domain.Authorization;

import Data.Database;
import Domain.Team;
import Domain.User;
import Domain.UserFactory;

import java.util.Date;
import java.util.LinkedList;

public class AdminAuthorization extends UserAuthorization {

    private LinkedList<Team> permanentlyClosedTeams;

    public AdminAuthorization(User user) {
        super(user);
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
    public Object[] addNewPlayer(String firstName, String lastName, String mail, Date birthDate, String role, double price){
         return UserFactory.getNewPlayer(firstName, lastName, mail, birthDate, role, price);
    }
    public Object[] addNewCoach(String firstName, String lastName, String mail, String training, String role, double price){
        return UserFactory.getNewCoach(firstName, lastName, mail, training, role, price);
    }
    public User addNewTeamOwner(String firstName, String lastName, String mail){
        return  UserFactory.getNewTeamOwner(firstName, lastName, mail);
    }
    public Object[] addNewTeamManager(String firstName, String lastName, String mail, double price){
        return UserFactory.getNewTeamManager(firstName, lastName, mail, price);
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
        if(checkIsTeamOwner(user)){
            LinkedList<Team> teams = getTeamsFromUser(user);
            if(teams!=null){
                for(Team team:teams )
                    if(team.getTeamOwners().size()==1)
                        return "";
            }
        }
        return Database.removeUser(userId);
    }

    private LinkedList<Team> getTeamsFromUser(User user) {
        for(AuthorizationRole role : user.getRoles()){
            if(role instanceof  TeamOwnerAuthorization) {
                return ((TeamOwnerAuthorization) role).getTeamsToManage();
            }
        }
        return null;
    }

    private boolean checkIsTeamOwner(User user) {
        for(AuthorizationRole role : user.getRoles()){
            if(role instanceof  TeamOwnerAuthorization)
                return true;
        }
        return false;
    }

    public void responseToComplaint(){}
    public void viewLig(){}
    public void trainModel(){}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AdminAuthorization;
    }



}
