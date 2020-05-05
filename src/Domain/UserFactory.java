package Domain;

import Data.Database;

import java.util.*;

public class UserFactory {

    public static User getNewFan(String password ,String firstName, String lastName, String mail, String phone, String address){
        try {
            User user = new User (firstName, lastName, "F", mail);
            Fan fan = new Fan(phone, address);
            user.addRole(fan);
            if(Database.addUser(password, user)){
                return user;
            }
            return null;
        }catch (Exception e){
            return null;
        }

    }

    public static User getNewPlayer(String firstName, String lastName, String mail, Date birthDate, String role, double price){
        try {
        User user = new User(firstName, lastName, "P", mail);
        Player player = new Player(user.getID(),user.getName(), birthDate, role, price, user);
        user.addRole(player);
        giveHasPageAuthorization(user);
        return addToDatabase(user, player);
        }
        catch (Exception e){
            return null;
        }
    }

    public static User getNewCoach(String firstName, String lastName, String mail, String training, String role, double price){
        try {
        User user = new User(firstName, lastName, "C", mail);
        Coach coach = new Coach(user.getID(),user.getName(), training, role, price);
        user.addRole(coach);
        giveHasPageAuthorization(user);
        return addToDatabase(user, coach);
    }
        catch (Exception e){
        return null;
    }
    }
    public static User getNewTeamOwner(String firstName, String lastName, String mail){
        try {
        User user = new User(firstName, lastName, "TO", mail);
        TeamOwner authorization = new TeamOwner();
        //authorization.fullPermission(true);
        user.addRole(authorization);
        return addToDatabase(user);
    }
        catch (Exception e){
                return null;
                }

    }

    public static User getNewTeamManager(String firstName, String lastName, String mail, double price, boolean manageAssets , boolean finance){
        try {
        User user = new User(firstName, lastName, "TM", mail);
        TeamManager teamManager = new TeamManager(user.getID(),user.getName(), price, manageAssets, finance);
        user.addRole(teamManager);
        return addToDatabase(user, teamManager);
        }
        catch (Exception e){
        return null;
        }

    }



    public static User getNewAdmin(String password, String firstName, String lastName, String mail){
        try {
        User user = new User(firstName, lastName, "A", mail);
        Admin authorization = new Admin();
        user.addRole(authorization);
        if(Database.addAdmin(password, user))
            return user;
        }
        catch (Exception e){
        return null;
        }
        return null;
    }
    public static User getNewUnionRepresentative(String firstName, String lastName, String mail){
        try {
        User user = new User(firstName, lastName, "UR", mail);
        UnionRepresentative authorization = new UnionRepresentative();
        user.addRole(authorization);
        return addToDatabase(user);
        }
        catch (Exception e){
        return null;
        }

    }

    public static User getNewReferee(String firstName, String lastName, String mail, String training){
        try {
        User user = new User(firstName, lastName, "R", mail);
        Referee referee = new Referee(training);
        user.addRole(referee);
        return user;
        }
        catch (Exception e){
        return null;
        }
    }


    private static User addToDatabase(User user) {
        String password = PasswordGenerator.generateRandPassword(6);
        if(Database.addUser(password, user)){
            MailSender.send(user.getMail(), "Welcome!\nUserId is: "+ user.getMail()+"\npassword: " + password);
            return user;
        }
        return null;
    }

    private static User addToDatabase(User user,Object asset){
        String password = PasswordGenerator.generateRandPassword(6);
        if(asset instanceof PartOfATeam){
            if(Database.addUser(password, user) && Database.addAsset((PartOfATeam)asset)){
                MailSender.send(user.getMail(), "Welcome!\nUserId is: "+ user.getMail()+"\npassword: " + password);
                return user;
            }
        }
        else{
            if(Database.addUser(password, user)){
                MailSender.send(user.getMail(), "Welcome!\nUserId is: "+ user.getMail()+"\npassword: " + password);
                return user;
            }
        }
        return null;
    }

    private static void giveHasPageAuthorization(User user) {
        String data = "This is "+ user.getName()+"'s Personal Page! ";
        PersonalPage page = new PersonalPage(data, user);
        HasPage authorization = new HasPage(page);
        user.addRole(authorization);
    }
}
