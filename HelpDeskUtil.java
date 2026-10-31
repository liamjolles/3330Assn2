package Proj;

import java.util.List;


 // Small utility class practicing generic methods and bounded wildcards.
 
public class HelpDeskUtil {

    private HelpDeskUtil() {
 
    }

    
    //  Prints each item on its own line using toString() 
    //  Generic method: works for a List of any element type.
     
    public static <T> void printAll(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("items must not be null");
        }
        for (T item : items) {
            System.out.println(item);
        }
    }

     // Returns the total service points across all technicians in the list.
     // The upper bounded wildcard lets this accept List<Technician> or a list
     // of any Technician subclass, elements are safely read as Technician.
     
    public static int sumPoints(List<? extends Technician> technicians) {
        if (technicians == null) {
            throw new IllegalArgumentException("technicians must not be null");
        }
        int total = 0;
        for (Technician t : technicians) {
            total += t.getServicePoints();
        }
        return total;
    }
}
