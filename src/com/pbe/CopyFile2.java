package com.pbe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile2 {
    public static void main(String[] args) {

        int x;
        if (args.length != 2) { // check that 2 filenames have been specified (assuming the given arguments are filenames..)
            System.out.println("Usage: CopyFile from to");
            return;
        }

        // single try statement that both opens the input and output files streams
        // after finishing the try block, both file_in and file_out will be closed, i.e.: automatic resource management
        // this setup streamlines code
        try (FileInputStream file_in = new FileInputStream(args[0]);
             FileOutputStream file_out = new FileOutputStream(args[1]))
        {
            do {
                x = file_in.read();
                if (x != -1) file_out.write(x);
            } while (x != -1);
        } catch(IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}