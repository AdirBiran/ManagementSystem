package Domain;

import java.util.Date;

public class Player extends Asset {

    private Date birthDate;
    private String role;
    private User user;


    public Player(String id,Date birthDate, String role, double price, User user) {
        super(id,price);
        this.birthDate = birthDate;
        this.role = role;
        this.user = user;
    }

    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @return
     */
    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }
}
