package Domain;


public class IdGenerator {

    private static int nextId =0;

    //each time the server is up
    public static void setNextId(int id){
        nextId = id;
    }

    public static int getNewId(){
        return nextId++;
    }

}
