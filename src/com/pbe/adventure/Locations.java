package com.pbe.adventure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// This class sets all locations
// Implementing Map
public class Locations implements Map<Integer, Location> {

    // Creating a hashmap to store locations
    // It holds:
    // 1. An integer value as unique identifier for the location
    // 2. A location object, which consists of its own unique identifier, description and directions object
    // The directions object contains one or more directions that the user can choose to go to from the given location
    private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

    // This block will be executed once, when the locations class is loaded
    // It sets all available locations
    static {
        // Creating a hashmap to store available directions, that will be connected to a location
        // It holds:
        // 1. the direction ("N", "S", "E", W") as String
        // 2. and a number which is the identifier for another location to which the given direction will lead to
        Map<String, Integer> directions = new HashMap<String, Integer>();

        // Adding a first location, with identifier '0' and no directions (null)
        locations.put(0, new Location(0, "You're sitting in front of a computer, figuring out Java",null));

        // Add another location, with identifier '1' and several directions
        directions = new HashMap<String, Integer>();
        directions.put("W", 2);
        directions.put("E", 3);
        directions.put("S", 4);
        directions.put("N", 5);
        locations.put(1, new Location(1, "You're standing at the end of a road, before a small brick house",directions));

        directions = new HashMap<String, Integer>();
        directions.put("N", 5);
        locations.put(2, new Location(2, "You're at the top of a grassy hill",directions));

        directions = new HashMap<String, Integer>();
        directions.put("W", 1);
        locations.put(3, new Location(3, "You're inside a small stone house, near a small spring",directions));

        directions = new HashMap<String, Integer>();
        directions.put("N", 1);
        directions.put("W", 2);
        locations.put(4, new Location(4, "You're in a valley, beside a stream",directions));

        directions = new HashMap<String, Integer>();
        directions.put("S", 1);
        directions.put("W", 2);
        locations.put(5, new Location(5, "You're in the forest",directions));
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