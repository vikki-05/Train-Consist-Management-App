import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

// ✅ UC14: Custom Exception
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// Passenger Bogie (UPDATED with validation)
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

    @Override
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

    @Override
    public String toString() {
        return type + " carrying " + cargo;
    }
}

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // UC1
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        // UC2
        trainConsist.add("Sleeper");
        trainConsist.add("AC Chair");
        trainConsist.add("First Class");
        trainConsist.remove("AC Chair");

        // UC3
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");

        // UC4
        LinkedList<String> linkedTrain = new LinkedList<>();
        linkedTrain.add("Engine");
        linkedTrain.add("Sleeper");
        linkedTrain.add("AC");
        linkedTrain.add("Cargo");
        linkedTrain.add("Guard");
        linkedTrain.add(2, "Pantry Car");
        linkedTrain.removeFirst();
        linkedTrain.removeLast();

        // UC5
        Set<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");

        // UC6
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 56);
        capacityMap.put("First Class", 24);

        // UC7–UC14 shared list
        List<Bogie> bogieList = new ArrayList<>();

        try {
            // Valid bogies
            bogieList.add(new Bogie("Sleeper", 72));
            bogieList.add(new Bogie("AC Chair", 56));
            bogieList.add(new Bogie("First Class", 24));

            // ❌ Invalid bogie (UC14 test)
            bogieList.add(new Bogie("Invalid Bogie", -10));

        } catch (InvalidCapacityException e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }

        // UC7: Sorting
        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        // UC8: Filtering
        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        // UC9: Grouping
        Map<String, List<Bogie>> grouped = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // UC10: Reduce
        int totalCapacity = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Capacity: " + totalCapacity);

        // UC11: Regex
        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        System.out.println("Train ID Valid: " + trainPattern.matcher("TRN-1234").matches());

        // UC12: Safety
        List<GoodsBogie> goodsList = new ArrayList<>();
        goodsList.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsList.add(new GoodsBogie("Open", "Coal"));

        boolean isSafe = goodsList.stream()
                .allMatch(b -> !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum"));

        System.out.println("Safety: " + (isSafe ? "SAFE" : "UNSAFE"));

        // UC13: Performance
        List<Bogie> largeList = new ArrayList<>();

        try {
            for (int i = 0; i < 100000; i++) {
                largeList.add(new Bogie("Sleeper", 72));
                largeList.add(new Bogie("AC Chair", 56));
            }
        } catch (InvalidCapacityException e) {
            e.printStackTrace();
        }

        long startLoop = System.nanoTime();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : largeList) {
            if (b.capacity > 60) loopResult.add(b);
        }
        long loopTime = System.nanoTime() - startLoop;

        long startStream = System.nanoTime();
        List<Bogie> streamResult = largeList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long streamTime = System.nanoTime() - startStream;

        System.out.println("Loop Time: " + loopTime);
        System.out.println("Stream Time: " + streamTime);

        // UC14  Final confirmation
        System.out.println("\nFinal Bogie List:");
        for (Bogie b : bogieList) {
            System.out.println(b);
        }

        System.out.println("\nSystem ready.");
    }
}