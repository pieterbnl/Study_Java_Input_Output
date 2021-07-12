package com.pbe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// **********************
// Copying a file
// **********************
// Requires a file to be specified in the directory of where CopyFile class is placed.
// Then to be run via command line specifying both the original and destination file, for example: java CopyFile.java filename1.txt filename2.txt
// Using a single try/catch block to monitor I/O operation on both files for exceptions and handle them if they occur.
// With a final statement that will always close the files.

public class CopyFile {

    public static void main(String[] args) {

        int i;
        FileInputStream file_in = null;
        FileOutputStream file_out = null;

        if (args.length != 2) { // check if both files have been specified
            System.out.println("Usage: CopyFile from to");
            return;
        }

        try { // copy a file
            file_in = new FileInputStream(args[0]);
            file_out = new FileOutputStream(args[1]);
            do {
                i = file_in.read(); // read character from input file
                if (i != -1)
                    file_out.write(i); // write character to output file, as long as not having reached the end of the input file
            } while (i != -1); // loop as long as not having reached end of file
        } catch(IOException e) { // catch any exceptions that may occur (in this example, no sub exceptions used)
            System.out.println("I/O error: " + e);
        } finally { // make sure to always close the files, using two separate try blocks
            try {
                if (file_in != null) file_out.close();
            } catch (IOException e2) {
                System.out.println("Can't close input file");
            }
            try {
                if (file_out != null) file_out.close();
            } catch (IOException e2) {
                System.out.println("Can't close output file");
            }

        }
    }
}