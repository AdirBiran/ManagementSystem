package Domain;

public abstract class User {

    protected String name;
    protected String userName;
    protected String password;


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     */
    public void logIn()
    {

    }

    /**
     *
     */
    public void logOut()
    {

    }

    /**
     *
     * @param strToSearch
     */
    public void Search(String strToSearch)
    {

    }

    /**
     *
     */
    public void editDetails()
    {

    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }
}
