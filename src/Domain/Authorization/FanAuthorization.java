package Domain.Authorization;

import Data.Database;
import Domain.*;

import java.util.List;

public class FanAuthorization extends UserAuthorization{

    private Fan fan;

    public FanAuthorization( Fan fan, User user) {
        super(user);
        this.fan = fan;
    }
    public void editPersonalInfo(String firstName, String lastName, String address, String phone){
        super.editPersonalInfo(firstName, lastName);
        fan.editDetails(address, phone);

    }
    public boolean followPage(PersonalPage page){
        return fan.addPageToFollow(page);
    }

    public boolean followGame(Game game , ReceiveAlerts receiveAlerts){
        return game.addFanForNotifications(fan, receiveAlerts);
    }
    public boolean submitComplaint(String description)
    {
        if(description.length()<1)
            return false;
        Complaint complaint = new Complaint(description, fan);
        return Database.addComplaint(complaint);

    }

    public List<PersonalPage> getFollowedPages()
    {
        return fan.getFollowPages();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FanAuthorization;
    }

    public Fan getFan() {
        return fan;
    }
    public String getRoleName(){return "FanAuthorization";}
}
