package Presentation;

import Domain.PersonalPage;
import Presentation.User;

import java.util.List;

public abstract class HasAPage extends User {

    protected PersonalPage page;

    public HasAPage(String name, String ID, String mail, PersonalPage page) {
        super(name, ID, mail);
        this.page = page;
    }

    /**
     *
     */
    public void uploadToPage()
    {

    }

}
