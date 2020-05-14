package Presentation.Records;

public class LeagueRecord implements Record{

    private String id;
    private String name;
    private String level;

    public LeagueRecord(String league) {
        String[]split = league.split(",");
        this.id=split[1].substring(split[1].indexOf("=")+2, split[1].indexOf(":"));
        split[1]=split[1].substring(split[1].indexOf(":")+1);
        this.name = split[1].substring(split[1].indexOf("=")+1);
        this.level = split[2].substring(split[2].indexOf("=")+1);
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getId() {
        return id;
    }
}
