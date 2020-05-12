package Presentation.Records;


public class PlayerRecord implements Record{

    private String id;
    private String name;
    private String birthDate;
    private String role;
    private String teams;

    public PlayerRecord(String playerToString){
        String[]split = playerToString.split(",");
        this.id = split[1].substring(split[1].indexOf("=")+2,split[1].indexOf(":"));
        split[1]=split[1].substring(split[1].indexOf(":"));
        this.name = split[1].substring(split[1].indexOf("=")+1);
        this.birthDate = fixDate(split[2]);
        this.role = split[3].substring(split[3].indexOf("=")+1);
        this.teams = split[4].substring(split[4].indexOf("=")+1);
    }

    private String fixDate(String date) {
        date = date.substring(date.indexOf("=")+1);
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

    public String getId() {
        return id;
    }

    public String getTeams() {
        return teams;
    }
}
