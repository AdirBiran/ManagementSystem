package Domain;

public class IdGenerator {
    private static int ids=0;

    public static int getNewId(){
        int id = ids;
        ids++;
        return id;
    }
}
