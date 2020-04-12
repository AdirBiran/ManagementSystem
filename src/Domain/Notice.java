package Domain;

public class Notice {

    private boolean active; // if true- no read yet
    private String message;

    public Notice(boolean active, String message) {
        this.active = active;
        this.message = message;
    }
}
