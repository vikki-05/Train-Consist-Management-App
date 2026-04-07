import java.util.Arrays;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // Unsorted Bogie IDs
        String[] bogieIds = {"BG309", "BG101", "BG550", "BG205", "BG412"};

        String searchKey = "BG205";

        // Step 1: Sort the array (IMPORTANT for Binary Search)
        Arrays.sort(bogieIds);

        System.out.println("Sorted Bogie IDs: " + Arrays.toString(bogieIds));

        // Step 2: Perform Binary Search
        boolean found = binarySearch(bogieIds, searchKey);

        // Step 3: Display Result
        if (found) {
            System.out.println("Bogie ID " + searchKey + " FOUND");
        } else {
            System.out.println("Bogie ID " + searchKey + " NOT FOUND");
        }
    }

    // Binary Search Method
    public static boolean binarySearch(String[] arr, String key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int comparison = key.compareTo(arr[mid]);

            if (comparison == 0) {
                return true; // Found
            } else if (comparison > 0) {
                low = mid + 1; // Search right
            } else {
                high = mid - 1; // Search left
            }
        }

        return false; // Not found
    }
}