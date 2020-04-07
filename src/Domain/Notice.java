package Domain;

public class Notice {

    private boolean active; // if true- no read yet
    private String message;
    private boolean toMail;

    public Notice(boolean toMail) {
        this.toMail = toMail;
    }

    public Notice(boolean active, String message) {
        this.active = active;
        this.message = message;
    }
}
