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

        // UC1
        System.out.println("=== Train Consist Management App ===");
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
        formation.add("Sleeper");

        // UC6
        Map<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 56);
        capacityMap.put("First Class", 24);

        // UC7
        List<Bogie> bogieList = new ArrayList<>();
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 56));
        bogieList.add(new Bogie("First Class", 24));
        bogieList.add(new Bogie("Sleeper", 72));

        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        // UC8
        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        // UC9
        Map<String, List<Bogie>> grouped = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // UC10
        int totalCapacity = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        // UC11
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        System.out.println("\nTrain ID Valid: " + trainPattern.matcher(trainId).matches());
        System.out.println("Cargo Code Valid: " + cargoPattern.matcher(cargoCode).matches());

        // UC12
        List<GoodsBogie> goodsList = new ArrayList<>();
        goodsList.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsList.add(new GoodsBogie("Open", "Coal"));
        goodsList.add(new GoodsBogie("Box", "Grain"));

        boolean isSafe = goodsList.stream()
                .allMatch(b ->
                        !b.type.equals("Cylindrical") || b.cargo.equals("Petroleum")
                );

        System.out.println("\nSafety Compliance: " + (isSafe ? "SAFE " : "UNSAFE "));

        // =========================
        // UC13  PERFORMANCE TEST
        // =========================
        System.out.println("\n--- UC13: Performance Comparison ---");

        // Create large dataset
        List<Bogie> largeList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeList.add(new Bogie("Sleeper", 72));
            largeList.add(new Bogie("AC Chair", 56));
            largeList.add(new Bogie("General", 50));
        }

        // 🔹 Loop-based filtering
        long startLoop = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : largeList) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        // 🔹 Stream-based filtering
        long startStream = System.nanoTime();

        List<Bogie> streamResult = largeList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        // Results
        System.out.println("Loop Result Size: " + loopResult.size());
        System.out.println("Stream Result Size: " + streamResult.size());

        System.out.println("Loop Execution Time (ns): " + loopTime);
        System.out.println("Stream Execution Time (ns): " + streamTime);

        // Comparison insight
        if (loopTime < streamTime) {
            System.out.println("Loop is faster ⚡");
        } else {
            System.out.println("Stream is faster ");
        }

        System.out.println("\nSystem ready.");
    }
}