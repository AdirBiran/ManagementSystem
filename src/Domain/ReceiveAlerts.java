package Domain;

public class ReceiveAlerts {
    boolean mail;
    boolean messageBox;
    boolean sms;

    public ReceiveAlerts(boolean toMail, boolean toPhone) {
        this.messageBox = true; //default
        this.mail = toMail;
        this.sms = toPhone;
    }
}
