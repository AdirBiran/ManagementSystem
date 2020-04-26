package Domain;


public class Referee {


    private String training;
    private User user;

    public Referee(String training, User user) {
        this.training = training;
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


    public void setTraining(String training) {
        this.training = training;
    }

    public User getUser() {
        return user;
    }
}
