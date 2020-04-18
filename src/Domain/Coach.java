package Domain;

public class Coach extends HasAPage implements Asset {

    private String training;
    private String role;
    private double price;


    public Coach(String firstName, String lastName, String mail, PersonalPage page, String training, String role, double price) {
        super(firstName,lastName, "C", mail, page);
        this.training = training;
        this.role = role;
        this.price = price;
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

    @Override
    public double getPrice() {
        return price;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
