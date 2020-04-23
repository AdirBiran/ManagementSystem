package Domain;

public class Notice {

    private boolean active; // if true- no read yet
    private String message;

    public Notice(String message) {
        this.active = true;
        this.message = message;
    }

    public void deactivate(){
        active = false;
    }

    public String getMessage() {
        return message;
    }
}
