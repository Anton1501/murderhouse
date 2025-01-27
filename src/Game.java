import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

import control.Parser;
import item.Item;
import room.Room;
import room.Schlafzimmer;
import room.view.View;

class Game {
    Optional<View> currentView = Optional.empty();
    Room currentRoom;
    HashMap<String, Item> inventory = new HashMap<String, Item>();
    Scanner scanner = new Scanner(System.in);
    Parser parser = new Parser();
    Optional<Item> heldItem = Optional.empty();
    HashMap<String, Room> rooms = new HashMap<String, Room>();

    public Game() {
        currentRoom = new Wohnzimmer();
        rooms.put("Schlafzimmer", new Schlafzimmer());
        rooms.put("Wohnzimmer", currentRoom);
        parser.setSimpleCommand("umsehen", () -> System.out.println(currentRoom.getDescription()));
        parser.setSimpleCommand("inventar", () -> {
            if (inventory.size() != 0) {
                System.out.println("Du hast folgende Gegenstände im Inventar:");
                for (String item : inventory.keySet()) {
                    System.out.println(item);
                }
            } else {
                System.out.println("Du hast keine Gegenstände im Inventar.");
            }
        });
        parser.setSimpleCommand("quit", () -> System.exit(0));
        parser.setSimpleCommand("hilfe", () -> {
            System.out.println("Du kannst folgende Befehle eingeben:");
            System.out.println("umsehen: Beschreibt den Raum");
            System.out.println("inventar: Zeigt das Inventar an");
            System.out.println("gehe <Richtung>: Geht in die angegebene Richtung");
            System.out.println("quit: Beendet das Spiel");
            System.out.println("hilfe: Zeigt diese Hilfe an");
            System.out.println("inspiziere <Ansicht>: Inspiziert die angegebene Ansicht");
            System.out.println("halte <Gegenstand>: Gegenstand aus dem Inventar halten");
            System.out.println("ansehen: Beschreibt den gehaltenen Gegenstand");
        });
        parser.setParamCommand("gehe", (String direction) -> {
            if (currentRoom.getAdjacentRooms().contains(direction)) {
                currentRoom = rooms.get(direction);
                currentView = null;
                System.out.println(currentRoom.getDescription());
            } else {
                System.out.println("Du kannst nicht in diese Richtung gehen.");
            }
        });
        parser.setParamCommand("inspiziere", (String view) -> {
            if (currentRoom.getViews().containsKey(view)) {
                currentView = Optional.of(currentRoom.getViews().get(view));
                System.out.println(currentView.get().getDescription());
            } else {
                System.out.println("Du kannst das nicht inspizieren.");
            }
        });
        parser.setParamCommand("halte", (String item) -> {
            if (inventory.containsKey(item)) {
                heldItem = Optional.of(inventory.get(item));
                System.out.println("Du hältst " + heldItem.get().getName() + ".");
            } else {
                System.out.println("Du hast das nicht im Inventar.");
            }
        });
        parser.setSimpleCommand("ansehen" , () -> {
            if (heldItem.isPresent()) {
                System.out.println(heldItem.get().getDescription());
            } else {
                System.out.println("Du hältst nichts.");
            }
        });
    }

    public void mainLoop() {
        System.out.print(">>> ");
        String input = scanner.nextLine();
        parser.parse(input);
    }

}
