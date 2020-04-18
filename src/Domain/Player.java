package Domain;

import java.util.Date;

public class Player extends HasAPage implements Asset {

    private Date birthDate;
    private String role;
    private double price;


    public Player(String firstName, String lastName, String mail, PersonalPage page, Date birthDate, String role, double price) {
        super(firstName,lastName, "P", mail, page);
        this.birthDate = birthDate;
        this.role = role;
        this.price = price;
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


    public void setRole(String role) {
        this.role = role;
    }

}
