package Domain;

import java.util.HashSet;
import java.util.Random;

public class IdGenerator {

    private static Random random = new Random(1000);
    private static final int PRIME_FACTOR=251;
    private static HashSet<Long> givenIds = new HashSet<>();

    public static long getNewId(){
        long id;
        do{
          id = random.nextInt()*PRIME_FACTOR;
          if(!givenIds.contains(id)&& id>0 ){
              givenIds.add(id);
              return id;
          }
        }
        while (givenIds.contains(id) || id<0);
        return -1;
    }
}
