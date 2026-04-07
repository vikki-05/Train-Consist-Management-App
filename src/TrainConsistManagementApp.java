import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

// UC14
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// UC15
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

// Passenger Bogie
class Bogie {
    String name;
    int capacity;

    public Bogie(String name, int capacity) throws InvalidCapacityException {
        if (capacity <= 0) {
            throw new InvalidCapacityException("Capacity must be greater than zero");
        }
        this.name = name;
        this.capacity = capacity;
    }

    public String toString() {
        return name + " (Capacity: " + capacity + ")";
    }
}

// Goods Bogie
class GoodsBogie {
    String type;
    String cargo;

    public GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    public void assignCargo(String cargo) {
        try {
            if (type.equals("Rectangular") && cargo.equals("Petroleum")) {
                throw new CargoSafetyException("Unsafe: Rectangular cannot carry Petroleum");
            }
            this.cargo = cargo;
            System.out.println("Assigned: " + this);
        } catch (CargoSafetyException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Assignment attempt done\n");
        }
    }

    public String toString() {
        return type + " carrying " + cargo;
    }
}

// MAIN CLASS
public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // UC1
        List<String> train = new ArrayList<>();
        System.out.println("Initial count: " + train.size());

        // UC2
        train.add("Sleeper");
        train.add("AC Chair");
        train.add("First Class");
        train.remove("AC Chair");

        // UC3
        Set<String> ids = new HashSet<>();
        ids.add("BG101");
        ids.add("BG101");

        // UC4
        LinkedList<String> list = new LinkedList<>();
        list.add("Engine");
        list.add("Sleeper");
        list.add("AC");
        list.add("Cargo");
        list.add("Guard");
        list.add(2, "Pantry");
        list.removeFirst();
        list.removeLast();

        // UC5
        Set<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");

        // UC6
        Map<String, Integer> map = new HashMap<>();
        map.put("Sleeper", 72);
        map.put("AC Chair", 56);

        // UC7+
        List<Bogie> bogies = new ArrayList<>();
        try {
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 56));
            bogies.add(new Bogie("First Class", 24));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // UC7
        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        // UC8
        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        // UC9
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // UC10
        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Capacity: " + total);

        // UC11
        Pattern p = Pattern.compile("TRN-\\d{4}");
        System.out.println(p.matcher("TRN-1234").matches());

        // UC12
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        boolean safe = goods.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        System.out.println("Safe: " + safe);

        // UC13
        long start = System.nanoTime();
        for (Bogie b : bogies) {
            if (b.capacity > 60) {}
        }
        long end = System.nanoTime();
        System.out.println("Time: " + (end - start));

        // UC15
        GoodsBogie g = new GoodsBogie("Rectangular", null);
        g.assignCargo("Petroleum");

        // UC16
        int[] arr = {72, 56, 24, 70};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        // UC17
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class"};
        Arrays.sort(bogieNames);

        // =========================
        // UC18 🔥 Linear Search
        // =========================
        System.out.println("\n--- UC18: Linear Search ---");

        String[] bogieIds = {
                "BG101",
                "BG205",
                "BG309",
                "BG412",
                "BG550"
        };

        String searchKey = "BG309"; // change to test

        boolean found = false;

        for (String id : bogieIds) {
            if (id.equals(searchKey)) {
                found = true;
                break; // early termination
            }
        }

        if (found) {
            System.out.println("Bogie ID " + searchKey + " FOUND in train.");
        } else {
            System.out.println("Bogie ID " + searchKey + " NOT FOUND.");
        }

        System.out.println("\nSystem ready.");
    }
}