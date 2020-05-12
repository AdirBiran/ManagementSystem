package Presentation.Records;

public class TeamRecord implements Record{
    private String id;
    private String name;
    private boolean active;
    private boolean permanentlyClosed;

    public TeamRecord(String teamToString){
        String[]split = teamToString.split("'");
        this.id = split[1];
        this.name = split[3];
        if(split[4].contains("active=true"))
            this.active=true;
        else
            this.active=false;

        if(split[4].contains("permanentlyClosed=false"))
            this.permanentlyClosed=false;
        else
            this.permanentlyClosed=true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPermanentlyClosed() {
        return permanentlyClosed;
    }
}
