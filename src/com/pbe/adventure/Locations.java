package com.pbe.adventure;

import java.io.*;
import java.util.*;

// This class sets all locations
// Implementing Map
public class Locations implements Map<Integer, Location> {

    // Creating a hashmap to store locations
    // It holds:
    // 1. An integer value as unique identifier for the location
    // 2. A location object, which consists of its own unique identifier, description and directions object
    // The directions object contains one or more directions that the user can choose to go to from the given location
    private static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    public static void main(String[]args) throws IOException { // specify exception

        // Create a FileWriter object
        // Pass filename as parameter to constructor
        // Loop through locations and use write method to write each location to the file
        // Finally, close the file
        // Note: failing to close a file is important as it can cause memory leaks
        // Note2: FileWriter triggers an IOException which is a checked exception
        // Therefore required to either specify or catch this exception
        // Applying a try with resources statement (a try statement that declares one or more resources)
        // Ensuring that each resource is closed at the end of the statement.
        System.out.println("Locations main() is run");
        try(BufferedWriter locationsFile = new BufferedWriter(new FileWriter("locations.txt"));
            BufferedWriter directionsFile = new BufferedWriter(new FileWriter("directions.txt"))) {
            for (Location location : locations.values()) { // loop through locations
                locationsFile.write(location.getID() + "," + location.getDescription() + "\n");
                for (String direction : location.getDirections().keySet()) { // loop through directions
                    if(!direction.equalsIgnoreCase("Q")) {
                        directionsFile.write(location.getID() + "," + direction + "," + location.getDirections().get(direction) + "\n");
                    }
                }
            }
        }
    }

    // This block will be executed once, when the locations class is loaded
    // It sets all available locations
    // Using FileReader wrapped in BufferedReader for a stream read of the locations file.
    // Using try by resources
    static {
        System.out.println("Locations static {} is run");
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
            scanner.useDelimiter(","); // separating fields by ,
            while(scanner.hasNextLine()) {
                int locationID = scanner.nextInt();
                scanner.skip(scanner.delimiter()); // moving past delimiter, moving to actual contents
                String locDescription = scanner.nextLine();
                System.out.println("Imported loc: " + locationID + ": " + locDescription);
                Map<String, Integer> tempDirection = new HashMap<>();
                locations.put(locationID, new Location(locationID, locDescription, tempDirection));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        // Read the directions
        // BufferedReader, reads text from input stream and buffers characters into a character array
        // Using BufferedReader: reads text from a character-input stream, , buffering characters for efficient reading of characters, arrays, and lines.
        // Passing file with directions -via FileReader (class for reading character files)- to BufferedReader constructor
        // Note: FileReader is the required input for the BufferedReader constructor
        try (BufferedReader directionsFile = new BufferedReader(new FileReader("directions_big.txt"))) {
            System.out.println("Locations try{} is run");
            String input;
            while((input = directionsFile.readLine()) !=null) {
                String[] data = input.split(","); // creates a string array, with each element containing a field
                int locationID = Integer.parseInt(data[0]);
                String direction = data[1];
                int destinationID = Integer.parseInt(data[2]);

                // display what has been read from the file
                System.out.println(locationID + ": " + direction + ": " + destinationID);
                Location location = locations.get(locationID);
                location.addDirection(direction, destinationID);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}