package Presentation.Records;

public class UserRecord implements Record {

    private String id;
    private String name;


    public UserRecord(String userToString) {
    }

    @Override
    public String getId() {
        return id;
    }
}
