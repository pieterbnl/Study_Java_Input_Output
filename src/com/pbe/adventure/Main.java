package com.pbe.adventure;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Simple text based adventure
// Player can input a direction to go somewhere
// Either by a single letter ("N" for north) or complete command ("go north")
// If a direction is not available, a message will be provided
// Locations are stored in a hash map (see Location and Locations class)
public class Main {

    private static Locations locations = new Locations();

    public static void main(String[] args) {

        // Setting up a scanner for user input via the command line
	    Scanner scanner = new Scanner(System.in);

	    // Creating a vocabulary with possible directions
        // Links a single character direction ("N") and a fully written direction ("NORTH")
        Map<String, String> vocabulary = new HashMap<String, String>();
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        // Set starting location
        int loc = 1;

        // Keep looping
        // The initial location with directions will be displayed
        // The user can input to choose a different location or choose to quit, which will end the loop
        while(true) {
            System.out.println(locations.get(loc).getDescription());

            // Location 0 is only reachable when the user has chosen to Quit
            // In that case, stop the loop
            if (loc == 0) {
                break;
            }

            // Get the directions for the current location and display them
            Map<String, Integer> directions = locations.get(loc).getDirections();
            System.out.print("Available directions are ");
            for (String direction: directions.keySet()) {
                System.out.print(direction + ", ");
            }
            System.out.println();

            // Get user input new direction
            // If the user has entered more than one character
            // Split up possible words and check for them in the vocabulary
            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1) {
                String[] words = direction.split(" "); // split words
                for (String word: words) { // loop through words
                    if (vocabulary.containsKey(word)) { // check if a word is present in the vocabulary, if yes:
                        direction = vocabulary.get(word); // set direction
                        break;
                    }
                }
            }

            // Check if the inputted direction is present in the directions map for the current location
            // If yes, the inputted direction's key will be used as new location when the loop starts again
            if (directions.containsKey(direction)) {
                loc = directions.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}