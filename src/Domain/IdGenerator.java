package Domain;

import java.util.HashSet;
import java.util.Random;

public class IdGenerator {

    private static Random random = new Random(1000);
    private static final int PRIME_FACTOR=10007;
    private static HashSet<Integer> givenIds = new HashSet<>();

    public static int getNewId(){
        int id;
        do{
          id = random.nextInt()*PRIME_FACTOR;
            if(!givenIds.contains(id)){
                givenIds.add(id);
                return id;
            }
        }
        while (givenIds.contains(id));
        return -1;
    }
}
