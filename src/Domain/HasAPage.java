package Domain;

public abstract class HasAPage extends User {

    protected PersonalPage page;

    public HasAPage(String firstName,String lastName,String ID, String mail, PersonalPage page) {
        super(firstName,lastName, ID, mail);
        this.page = page;
    }

    /**
     *
     */
    public void uploadToPage(String data)
    {

    }

    public void setPage(PersonalPage personalPage){
        page = personalPage;
    }
}
