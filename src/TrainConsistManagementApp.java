import java.util.Arrays;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // Example 1: EMPTY array (will throw exception)
        String[] bogieIds = {};

        String searchKey = "BG101";

        try {
            boolean found = searchBogie(bogieIds, searchKey);

            if (found) {
                System.out.println("Bogie ID " + searchKey + " FOUND");
            } else {
                System.out.println("Bogie ID " + searchKey + " NOT FOUND");
            }

        } catch (IllegalStateException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // Example 2: VALID case (works normally)
        System.out.println("\n--- Running with valid data ---");

        String[] validBogies = {"BG101", "BG205", "BG309"};

        try {
            boolean found = searchBogie(validBogies, "BG205");

            if (found) {
                System.out.println("Bogie ID BG205 FOUND");
            } else {
                System.out.println("Bogie ID BG205 NOT FOUND");
            }

        } catch (IllegalStateException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Search method with fail-fast validation
    public static boolean searchBogie(String[] bogies, String key) {

        //  FAIL-FAST CHECK
        if (bogies == null || bogies.length == 0) {
            throw new IllegalStateException("Cannot perform search: No bogies available in train.");
        }

        // Linear Search (can also use Binary if sorted)
        for (String bogie : bogies) {
            if (bogie.equals(key)) {
                return true;
            }
        }

        return false;
    }
}