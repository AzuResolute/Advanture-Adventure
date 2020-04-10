import java.util.*;

public class Main {

    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();
    private static Map<String, String> keyboardToDir = new HashMap<String, String>();
    private static Map<String, String> dirToKeyboard = new HashMap<String, String>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> validDirections = Arrays.asList("NORTH", "EAST", "SOUTH", "WEST", "QUIT");
        keyboardToDir.put("W", "North");
        keyboardToDir.put("A", "West");
        keyboardToDir.put("S", "South");
        keyboardToDir.put("D", "East");
        keyboardToDir.put("Q", "Quit");

        dirToKeyboard.put("N", "W");
        dirToKeyboard.put("W", "A");
        dirToKeyboard.put("S", "S");
        dirToKeyboard.put("E", "D");
        dirToKeyboard.put("Q", "Q");

        locations.put(0, new Location(0, "Exit"));
        locations.put(1, new Location(1, "Forest Hills"));
        locations.put(2, new Location(2, "Long Island City"));
        locations.put(3, new Location(3, "Manhattan"));
        locations.put(4, new Location(4, "Incheon"));
        locations.put(5, new Location(5, "San Francisco"));
        locations.put(6, new Location(6, "Pateros"));
        locations.put(7, new Location(7, "Delta"));
        locations.put(8, new Location(8, "MMS"));
        locations.put(9, new Location(9, "Central"));

        locations.get(1).addExit("A", 2);
        locations.get(1).addExit("D", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("W", 5);

        locations.get(2).addExit("W", 5);

        locations.get(3).addExit("A", 1);

        locations.get(4).addExit("W", 1);
        locations.get(4).addExit("A", 2);

        locations.get(5).addExit("A", 2);
        locations.get(5).addExit("S", 1);

        int loc = 1;
        while(true) {
            System.out.println(locations.get(loc).getDescription());
            if(loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.println("Available exits are:");

            exits.keySet().forEach(k ->
                    System.out.println(
                            "To go to "
                            + locations.get(exits.get(k)).getDescription()
                            + " go "
                            + keyboardToDir.get(k)
                            + " ("
                            + k
                            + ")"
                    ));

            String direction =
                    Character.toString(
                        Arrays.stream(
                            scanner.nextLine().split(" ")
                        )
                            .filter(w -> validDirections.contains(w.toUpperCase()))
                            .findFirst()
                            .get()
                        .charAt(0)
                    ).toUpperCase();

            System.out.println("Direction ----> " + direction);
            String control = dirToKeyboard.get(direction);
            if(exits.containsKey(control)) {
                loc = exits.get(control);
                System.out.println(loc);
            } else {
                System.out.println("Cannot enter");
            }

            if(!locations.containsKey(loc)) {
                System.out.println("You cannot go this direction");
            }
        }
    }
}
