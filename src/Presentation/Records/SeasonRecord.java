package Presentation.Records;

public class SeasonRecord implements Record {

    private String id;
    private String year;

    public SeasonRecord(String seasonToString){
        String[]split = seasonToString.split(",");
        this.id = split[1].substring(split[1].indexOf("=")+2, split[1].indexOf(":"));
        split[1]=split[1].substring(split[1].indexOf(":"));
        this.year = split[1].substring(split[1].indexOf("=")+1);


    }

    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }
}
