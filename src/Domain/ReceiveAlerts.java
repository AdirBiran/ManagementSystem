package Domain;

public class ReceiveAlerts {
    boolean mail;
    boolean messageBox;
    boolean sms;

    public ReceiveAlerts(boolean toMail, boolean toPhone) {
        this.mail = toMail;
        this.messageBox = true; //default
        this.sms = toPhone;
    }
}
