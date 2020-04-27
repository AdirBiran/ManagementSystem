package Domain;

public abstract class HasPage {

    private PersonalPage page;

    public HasPage(PersonalPage page) {

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
        return obj instanceof HasPage;
    }
}
