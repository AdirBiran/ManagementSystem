package Presentation.Records;

public class GameRecord implements Record {


    private String id;
    private String name;
    private String date;
    private String follows;

    public GameRecord(String toString) {
        String[] split = toString.split(",");
        this.id = split[0];
        this.name = split[1];
        this.date = split[2];
        if(split[3].equals("+"))
            this.follows = "you follow this game";
        else
            this.follows = "";
    }

    public String getDate() {
        return date;
    }

    public String getFollows() {
        return follows;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
