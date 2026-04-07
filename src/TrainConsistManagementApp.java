import java.util.*;
import java.util.stream.Collectors;

// Bogie class inside same file (NOT public)
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

        System.out.println(trainConsist);

        trainConsist.remove("AC Chair");
        System.out.println(trainConsist);

        System.out.println("Contains Sleeper? " + trainConsist.contains("Sleeper"));

        // UC3
        System.out.println("\n--- UC3 ---");

        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");

        System.out.println(bogieIds);

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

        System.out.println(linkedTrain);

        // UC5
        System.out.println("\n--- UC5 ---");

        Set<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println(formation);

        // UC6
        System.out.println("\n--- UC6 ---");

        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 56);
        capacityMap.put("First Class", 24);

        for (Map.Entry<String, Integer> e : capacityMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // UC7
        System.out.println("\n--- UC7 ---");

        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 56));
        bogieList.add(new Bogie("First Class", 24));

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("Sorted bogies:");
        for (Bogie b : bogieList) {
            System.out.println(b);
        }

        // UC8 🔥 STREAM FILTERING
        System.out.println("\n--- UC8: Filter Bogies (Capacity > 60) ---");

        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("Filtered bogies:");
        for (Bogie b : filtered) {
            System.out.println(b);
        }

        System.out.println("\nSystem ready.");
    }
}