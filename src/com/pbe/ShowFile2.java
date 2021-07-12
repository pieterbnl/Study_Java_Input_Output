package com.pbe;

// **********************
// Displaying a text file
// **********************
// Alternative to ShowFile, wrapping portions of a program that open the file and access the file within a single try block
// (instead of separating the two) and then use a finally block to close the file.
//
// Requires a file to be specified in the directory of where ShowFile class is placed.
// Then to be run via command line, for example: java ShowFile.java readme.txt
// Using try/catch blocks to monitor each I/O operation for exceptions and handle them if they occur.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShowFile2 {

    public static void main(String[] args) {

        int i;
        FileInputStream fin = null; // fin initialized to null

        if (args.length != 1) { // confirm a filename is specified
            System.out.println("Usage: ShowFile filename");
            return;
        }

        try { // attempt to open the file - providing catches for various potential exceptions and always closing the file in the end
            fin = new FileInputStream(args[0]);
            do {
                i = fin.read();
                if (i != -1) System.out.println((char) i);
            } while (i != -1);
        } catch (FileNotFoundException e) { // note this is a subclass of IOException meaning it could be left out, but it would limit feedback
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("An I/O error occurred");
        } finally { // close file in any case
            try {
                if (fin != null) fin.close(); // finn will be non-null only if the file is successfully opened
            } catch(IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}