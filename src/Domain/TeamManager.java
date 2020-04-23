package Domain;

public class TeamManager extends Asset {

    private User user;


    public TeamManager(String id,double price, User user) {
        super(id, price);
        this.user = user;
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    public User getUser() {
        return user;
    }
}
