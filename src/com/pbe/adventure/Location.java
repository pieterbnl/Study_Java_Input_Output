package com.pbe.adventure;

import java.util.HashMap;
import java.util.Map;

// Stores locations
public class Location {

    private final int locationID; // Unique identifier of location
    private final String description; // Description of location
    private final Map<String, Integer> directions; // Directions that are accessible from this location

    // Constructor
    public Location(int locationID, String description, Map<String, Integer> directions) {
        this.locationID = locationID;
        this.description = description;
        if(directions != null) {
            this.directions = new HashMap<String, Integer>(directions);
        } else {
            this.directions = new HashMap<String, Integer>();
        }
        this.directions.put("Q", 0); // Q is added to each location so that the user can choose to Quit at any time
    }

//    public void addDirection(String direction, int location) {
//        directions.put(direction, location);
//    }

    public int getID() {
        return locationID;
    }

    // Get description of location
    public String getDescription() {
        return description;
    }

    // Get directions of location
    public Map<String, Integer> getDirections() {
        return new HashMap<String, Integer>(directions);
    }

    protected void addDirection(String direction, int locationID) {
        directions.put(direction, locationID);
    }
}
