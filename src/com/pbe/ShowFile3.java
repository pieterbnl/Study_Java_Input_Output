package com.pbe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// **********************
// Displaying a text file
// **********************
// Includes automatic closure of a file with use of try-with-resources, after a file is no longer needed
public class ShowFile3 {

    public static void main(String[] args) {
        int x;

        if (args.length != 1) { // check that a filename has been specified (assuming the given argument is an filename..)
            System.out.println("Usage: ShowFile filename");
            return;
        }

        // using try with resources which will result in closure of the file when the try block is left
        // the resource stream connected to the file is opened within the try statement,
        // this makes file_in implicitly final (meaning you can't assign a resource after it's created) and making file_in a local variable
        // upon leaving the try statement, the stream associated with file_in is automatically closed by an implicit call to close()
        // this way it's not possible to forget to close the file
        try (FileInputStream file_in = new FileInputStream(args[0])) {
            do {
                x = file_in.read(); // read a character from the file
                if (x != -1) System.out.println((char) x); // if end of file is not reached, print a character from the file
            } while (x != -1); // continue to loop as long as the end of file (which returns -1) is not reached
        } catch (FileNotFoundException e) { // catch potential file not found exception
            System.out.println("No file found");
        } catch (IOException e) { // catch any other potential exception
            System.out.println("I/O error occurred");
        }
    }
}