package Domain;

public class Coach extends Asset {

    private String training;
    private String role;
    private User user;


    public Coach(String id,String training, String role, double price, User user) {
        super(id, price);
        this.training = training;
        this.role = role;
        this.user = user;
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


    public void setTraining(String training) {
        this.training = training;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }
}
