package com.pbe;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// **********************
// Displaying a text file
// **********************
// Requires a file to be specified in the directory of where ShowFile class is placed.
// Then to be run via command line, for example: java ShowFile.java readme.txt
// Using try/catch blocks to monitor each I/O operation for exceptions and handle them if they occur.

public class ShowFile {

    public static void main(String[] args) {

        int i;
        FileInputStream fin;

        if(args.length != 1) { // confirm a filename is specified
            System.out.println("Usage: ShowFile filename");
            return;
        }

        try { // attempt to open the file
            fin = new FileInputStream(args[0]);
        } catch (FileNotFoundException e) { // file not found
            System.out.println("Can't open file");
            return;
        }

//        try { // file is open and can be read
//            do { // read characters until end of file is reached
//                i = fin.read();
//                if(i != -1) System.out.println((char) i); // not end of file, print character
//            } while(i != -1); // continue loop as long as end of file is not reached
//        } catch (IOException e) {  // exception in case somethings happens with the file while reading from it
//            System.out.println("Error reading file");
//        }
//
//        try { // close the file
//            fin.close();
//        } catch (IOException e) {
//            System.out.println("Can't close file");
//        }

        // Alternative to close the file, with all methods that access the file contained with a try block
        // and the finally block used to close the file.
        // This way, no matter how the try block terminates, the file is closed.
        try {
            do {
                i = fin.read();
                if (i != -1) System.out.println((char) i);
            } while (i != -1);
        } catch(IOException e) {
            System.out.println("Error reading file");;
        } finally { // closing file on the way out of the try block
            try {
                fin.close();
            } catch (IOException e) {
                System.out.println("Can't close file");
            }
        }
    }
}
