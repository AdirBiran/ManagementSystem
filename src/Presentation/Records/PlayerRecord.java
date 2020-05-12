package Presentation.Records;

import java.util.Date;

public class PlayerRecord implements Record{

    private String name;
    private String birthDate;
    private String role;
    private String teams;

    public PlayerRecord(String playerToString){
        String[]split = playerToString.split("'");
        this.name = split[1];
        this.birthDate = fixDate(split[2]);
        this.role = split[3];


    }

    private String fixDate(String date) {
        date = date.substring(1);
        date = date.substring(date.indexOf('=')+1, date.indexOf(','));
        String[]splitDate = date.split(" ");
        date = splitDate[1]+" "+splitDate[2]+" "+splitDate[5];
        return date;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getRole() {
        return role;
    }
}
