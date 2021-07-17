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
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

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
        try(FileWriter locationsFile = new FileWriter("locations.txt");
            FileWriter directionsFile = new FileWriter("directions.txt")) {
            for (Location location : locations.values()) { // loop through locations
                locationsFile.write(location.getID() + ", " + location.getDescription() + "\n");
                for (String direction : location.getDirections().keySet()) { // loop through directions
                    directionsFile.write(location.getID() + "," + direction + "," + location.getDirections().get(direction) + "\n");
                }
            }
        }

//        FileWriter locationsFile = null; // note: declared outside the try, catch block to have a scope that makes it available to both
//        try {
//            locationsFile = new FileWriter("locations.txt");
//            for (Location location : locations.values()) {
//                locationsFile.write(location.getID() + ", " + location.getDescription() + "\n");
//            }
//        } finally {
//            assert locationsFile != null;
//            if(locationsFile !=null) {
//                locationsFile.close(); // close the stream - placed in finally to make sure it is always executed
//            }
//        }
    }

    // This block will be executed once, when the locations class is loaded
    // It sets all available locations
    static {
        Scanner scanner = null;

        try {
            System.out.println("Locations static {} is run");
            scanner = new Scanner(new FileReader("locations.txt"));
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
        } finally {
            assert scanner != null;
            if(scanner !=null) {
                scanner.close();
            }
        }

        // Read the directions
        // Bufferedreader, reads text from input stream and buffers characters into a character array
        // Using FileReader as input stream for Buffered Reader
        try {
            // pass file reader to BufferedReader constructor
            System.out.println("Locations try{} is run");
            scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")));
            scanner.useDelimiter(",");

            // read the file, line by line
            while(scanner.hasNextLine()) {
                // extract the relevant info piece by piece
//                int locationID = scanner.nextInt(); // first number is the location identifier
//                scanner.skip(scanner.delimiter()); // skipping over the deliminator ,
//                String direction = scanner.next(); // followed by a (single character) direction
//                scanner.skip(scanner.delimiter()); // skipping over the deliminator ,
//                String destination = scanner.nextLine();
//                int destinationID = Integer.parseInt(destination); // followed by the destination identifier

                String input = scanner.nextLine();
                String[] data = input.split(","); // creates a string array, with each element containing a field
                int locationID = Integer.parseInt(data[0]);
                String direction = data[1];
                int destinationID = Integer.parseInt(data[2]);

                // display what has been read from the file
                System.out.println(locationID + ": " + direction + ": " + destinationID);

                //
                Location location = locations.get(locationID);
                location.addDirection(direction, destinationID);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert scanner != null;
            if(scanner !=null) {
                scanner.close();
            }
        }


//        // Creating a hashmap to store available directions, that will be connected to a location
//        // It holds:
//        // 1. the direction ("N", "S", "E", W") as String
//        // 2. and a number which is the identifier for another location to which the given direction will lead to
//        Map<String, Integer> directions = new HashMap<String, Integer>();
//
//        // Adding a first location, with identifier '0' and no directions (null)
//        locations.put(0, new Location(0, "You're sitting in front of a computer, figuring out Java",null));
//
//        // Add another location, with identifier '1' and several directions
//        directions = new HashMap<String, Integer>();
//        directions.put("W", 2);
//        directions.put("E", 3);
//        directions.put("S", 4);
//        directions.put("N", 5);
//        locations.put(1, new Location(1, "You're standing at the end of a road, before a small brick house",directions));
//
//        directions = new HashMap<String, Integer>();
//        directions.put("N", 5);
//        locations.put(2, new Location(2, "You're at the top of a grassy hill",directions));
//
//        directions = new HashMap<String, Integer>();
//        directions.put("W", 1);
//        locations.put(3, new Location(3, "You're inside a small stone house, near a small spring",directions));
//
//        directions = new HashMap<String, Integer>();
//        directions.put("N", 1);
//        directions.put("W", 2);
//        locations.put(4, new Location(4, "You're in a valley, beside a stream",directions));
//
//        directions = new HashMap<String, Integer>();
//        directions.put("S", 1);
//        directions.put("W", 2);
//        locations.put(5, new Location(5, "You're in the forest",directions));
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