package Domain;

public class TeamManager extends Manager implements Asset {

    private double price;

    public TeamManager(String firstName, String lastName, String mail, Team team, double price) {
        super(firstName,lastName, "TM", mail);
        this.price = price;
        addTeam(team);
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++

    @Override
    public double getPrice() {
        return price;
    }
}
