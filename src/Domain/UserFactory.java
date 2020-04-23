package Domain;

import Data.Database;
import Domain.Authorization.*;

import java.util.*;

public class UserFactory {


    /*
    Object[0] = User
    Object[1] = Fan
     */


    public static Object[] getNewFan(String password ,String firstName, String lastName, String mail, String phone, String addres){
        try {
            User user = new User (firstName, lastName, "F", mail);
            Fan fan = new Fan(user.getID(),phone, addres);
            FanAuthorization authorization = new FanAuthorization(fan, user);
            user.addAuthorization(authorization);
            if(Database.addUser(password, user)){
                return addToArray(user, fan);
            }
            return null;
        }catch (Exception e){
            return null;
        }

    }

    public static Object[] getNewPlayer(String firstName, String lastName, String mail, Date birthDate, String role, double price){
        try {
        User user = new User(firstName, lastName, "P", mail);
        Player player = new Player(user.getID(), birthDate, role, price, user);
        giveHasPageAuthorization(user);
        return addToDatabase(user, player);
        }
        catch (Exception e){
            return null;
        }
    }

    public static Object[] getNewCoach(String firstName, String lastName, String mail, String training, String role, double price){
        try {
        User user = new User(firstName, lastName, "C", mail);
        Coach coach = new Coach(user.getID(),training, role, price, user);
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
        TeamOwnerAuthorization authorization = new TeamOwnerAuthorization(user);
        authorization.giveAll(true);
        user.addAuthorization(authorization);
        return addToDatabase(user);
    }
        catch (Exception e){
                return null;
                }

    }

    public static Object[] getNewTeamManager(String firstName, String lastName, String mail, double price){
        try {
        User user = new User(firstName, lastName, "TM", mail);
        TeamManager teamManager = new TeamManager(user.getID(),price, user);
        TeamOwnerAuthorization authorization = new TeamOwnerAuthorization(user);
        authorization.giveAssetManagement();
        authorization.giveFinance();
        user.addAuthorization(authorization);
        return addToDatabase(user, teamManager);
        }
        catch (Exception e){
        return null;
        }

    }

    public static User getNewAdmin(String password, String firstName, String lastName, String mail){
        try {
        User user = new User(firstName, lastName, "A", mail);
        AdminAuthorization authorization = new AdminAuthorization(user);
        user.addAuthorization(authorization);
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
        UnionAuthorization authorization = new UnionAuthorization(user);
        user.addAuthorization(authorization);
        return addToDatabase(user);
        }
        catch (Exception e){
        return null;
        }

    }

    public static Object[] getNewReferee(String firstName, String lastName, String mail, String training){
        try {
        User user = new User(firstName, lastName, "R", mail);
        Referee referee = new Referee(training, user);
        RefereeAuthorization authorization = new RefereeAuthorization(referee, user);
        user.addAuthorization(authorization);
        return addToArray(user, referee);
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


    private static Object[] addToDatabase(User user,Object asset){
        String password = PasswordGenerator.generateRandPassword(6);
        if(asset instanceof Asset){
            if(Database.addUser(password, user) && Database.addAsset((Asset)asset)){
                MailSender.send(user.getMail(), "Welcome!\nUserId is: "+ user.getMail()+"\npassword: " + password);
                return addToArray(user, asset);
            }
        }
        else{
            if(Database.addUser(password, user)){
                MailSender.send(user.getMail(), "Welcome!\nUserId is: "+ user.getMail()+"\npassword: " + password);
                return addToArray(user, asset);
            }
        }
        return null;
    }

    private static Object[] addToArray(User user, Object asset) {
        Object[] userObj = new Object[2];
        userObj[0] = user;
        userObj[1] = asset;
        return userObj;
    }

    private static void giveHasPageAuthorization(User user) {
        String data = "This is "+ user.getName()+"'s Personal Page! ";
        PersonalPage page = new PersonalPage(data, user);
        HasPageAuthorization authorization = new HasPageAuthorization(page, user);
        user.addAuthorization(authorization);
    }


}
