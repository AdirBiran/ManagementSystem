package Domain;

import java.util.HashSet;
import java.util.Random;

public class IdGenerator {

    private static Random random = new Random();
    private static HashSet<Long> givenIds = new HashSet<>();

    public static long getNewId(){
        long id;
        do{
          id = random.nextInt();
          id = id/10000;
          if(!givenIds.contains(id)&& id>0 ){
              givenIds.add(id);
              return id;
          }
        }
        while (givenIds.contains(id) || id<0);
        return -1;
    }
}
