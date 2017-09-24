package insertJPA;

import java.util.HashMap;
import javax.persistence.Persistence;

public class makeTables {

    public static void main(String[] args) {
        try {
            Persistence.generateSchema("pu", new HashMap());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
