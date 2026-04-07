import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// Passenger Bogie
class Bogie {
    String name;
    int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return name + " (Capacity: " + capacity + ")";
    }
}

// Goods Bogie (UC12)
class GoodsBogie {
    String type;
    String cargo;

    public GoodsBogie(String type, String cargo) {
        this.type = type;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return type + " carrying " + cargo;
    }
}

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        // UC1
        System.out.println("=== Train Consist Management App ===");
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        // UC2
        System.out.println("\n--- UC2 ---");
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        System.out.println("After adding: " + trainConsist);

        trainConsist.remove("AC Chair");
        System.out.println("After removal: " + trainConsist);

        System.out.println("Contains Sleeper? " + trainConsist.contains("Sleeper"));

        // UC3
        System.out.println("\n--- UC3 ---");
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");
        System.out.println("Unique Bogie IDs: " + bogieIds);

        // UC4
        System.out.println("\n--- UC4 ---");
        LinkedList<String> linkedTrain = new LinkedList<>();
        linkedTrain.add("Engine");
        linkedTrain.add("Sleeper");
        linkedTrain.add("AC");
        linkedTrain.add("Cargo");
        linkedTrain.add("Guard");

        linkedTrain.add(2, "Pantry Car");
        linkedTrain.removeFirst();
        linkedTrain.removeLast();

        System.out.println("Train order: " + linkedTrain);

        // UC5
        System.out.println("\n--- UC5 ---");
        Set<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("Formation: " + formation);

        // UC6
        System.out.println("\n--- UC6 ---");
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 56);
        capacityMap.put("First Class", 24);

        for (Map.Entry<String, Integer> entry : capacityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // UC7
        System.out.println("\n--- UC7 ---");
        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 56));
        bogieList.add(new Bogie("First Class", 24));
        bogieList.add(new Bogie("Sleeper", 72));

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("Sorted bogies:");
        for (Bogie b : bogieList) {
            System.out.println(b);
        }

        // UC8
        System.out.println("\n--- UC8 ---");
        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("Filtered bogies (>60 capacity):");
        for (Bogie b : filtered) {
            System.out.println(b);
        }

        // UC9
        System.out.println("\n--- UC9 ---");
        Map<String, List<Bogie>> grouped = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        for (Map.Entry<String, List<Bogie>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // UC10
        System.out.println("\n--- UC10 ---");
        int totalCapacity = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total seating capacity: " + totalCapacity);

        // UC11
        System.out.println("\n--- UC11: Regex Validation ---");

        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        String trainRegex = "TRN-\\d{4}";
        String cargoRegex = "PET-[A-Z]{2}";

        Pattern trainPattern = Pattern.compile(trainRegex);
        Pattern cargoPattern = Pattern.compile(cargoRegex);

        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        if (trainMatcher.matches()) {
            System.out.println("Valid Train ID: " + trainId);
        } else {
            System.out.println("Invalid Train ID: " + trainId);
        }

        if (cargoMatcher.matches()) {
            System.out.println("Valid Cargo Code: " + cargoCode);
        } else {
            System.out.println("Invalid Cargo Code: " + cargoCode);
        }

        // UC12
        System.out.println("\n--- UC12: Safety Compliance Check ---");

        List<GoodsBogie> goodsList = new ArrayList<>();
        goodsList.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsList.add(new GoodsBogie("Open", "Coal"));
        goodsList.add(new GoodsBogie("Box", "Grain"));

        // Uncomment below to test failure case
        // goodsList.add(new GoodsBogie("Cylindrical", "Coal"));

        boolean isSafe = goodsList.stream()
                .allMatch(b ->
                        !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum")
                );

        System.out.println("Goods Bogies:");
        for (GoodsBogie g : goodsList) {
            System.out.println(g);
        }

        if (isSafe) {
            System.out.println("Train is SAFETY COMPLIANT ");
        } else {
            System.out.println("Train is NOT SAFE ");
        }

        System.out.println("\nSystem ready.");
    }
}