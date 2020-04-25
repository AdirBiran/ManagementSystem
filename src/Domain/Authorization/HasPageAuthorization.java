package Domain.Authorization;

import Domain.PersonalPage;
import Domain.User;

public class HasPageAuthorization extends UserAuthorization {

    private PersonalPage page;

    public HasPageAuthorization(PersonalPage page, User user) {
        super(user);
        this.page = page;
    }

    public void uploadToPage(String data)
    {
        page.addData(data);
    }

    public PersonalPage getPage() {
        return page;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HasPageAuthorization;
    }



}
