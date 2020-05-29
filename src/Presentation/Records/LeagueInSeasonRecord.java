package Presentation.Records;

public class LeagueInSeasonRecord implements Record {

    private String id;
    private String league;
    private String season;

    public LeagueInSeasonRecord(String toString) {
        String[] split = toString.split("=");
        league = split[1].substring(0, split[1].indexOf(","));
        season = split[2];
        id = league+season;
    }

    public String getLeague() {
        return league;
    }

    public String getSeason() {
        return season;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return league + " " + season;
    }
}
