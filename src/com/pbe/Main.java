package com.pbe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Study on Java I/O and related topics/keywords (try-with-resources, transient, volatile, instanceof, strictfp, assert)
 @author Pieter Beernink
 @version 1.0
 @since 1.0
 */

// *********************
// Java I/O
// *********************

// Streams
// Java.io package supports Java basic -cohesive and consistent- I/O (Input/Output) system, including file I/O.
// Java provides strong, flexible support for I/O related to files and networks.
// Java programs perform I/O through streams, which are implemented within class hierarchies defined in the java.io package.
// A stream is an abstraction that either produces or consumes information and is linked to a physical device by the Java I/O system.
// No matter the device, all streams behave in the same manner: the same I/O classes and methods can be applied to different types of devices.
// I/O streams can abstract different kinds of inputs and outputs like disk files, keyboard input, network socket/connection, etc.

// Byte streams & Character streams
// Two types of streams are defined:
// 1. Byte streams - a convenient means for handling I/O of bytes, used for example to read/write binary data.
//    Byte streams are defined by two class hierarchies, with at the top two abstract classes: InputStream & OutputStream.
//    Each has several concrete subclasses that handle differences among various devices (disk files, network connections, memory buffers, ..).
// 2. Character streams - a convenient means(and sometimes more efficient) for handling I/O of characters, using Unicode, allowing it to be internationalized.
//     Java 1.0 only knew byte streams. Character streams where added in 1.1. Old code should be updated to take advantage of character streams.
//    Character streams are defined by using two class hierarchies, of which at the top the two abstract classes Reader and Writer.
//    They handle Unicode character streams. Java has several concrete subclasses of each.
//
// Check Byte Stream and Character classes in java.io to see the several key methods that other stream classes implement.
// The most import methods for both byte- and character streams are the read() and write() methods.
// Respectively used for reading and writing of bytes of data and characters of data,
// Each of these methods has a form that is abstract and must be overridden by derived stream classes.

// Predefined streams
// All java programs automatically import the java.lang package, which defines class System.
// System contains three predefined stream variables:
// 1. System.in (object of type InputStream) - refers to standard input, which by default is the keyboard
// 2. System.out (object of type PrintStream) - refers to the standard output stream, which by default is the console
// 3. System.err (object of type PrintStream) - refers to the standard error stream, which by default is the console as well
// Each of these streams may be redirected to any compatible I/O device.
// All 3 stream variables are declared as public, static, final.
// This means they can be used by any part of a program and without reference to a specific System object.
// All 3 streams are byte steams. They can be wrapped within character-bases streams if needed.

// Reading console input
// Console input is accomplished by reading from System.in.
// To keep a program easier to internationalize and maintain, the preferred method of reading console input is via a character stream.
// To obtain a character based stream attached to the console, wrap System.in in a BufferedReader object: BufferedReader (Reader inputReader)
// inputReader is the stream that's linked to the instance of BufferedReader that is being created.
// Reader is an abstract class, of which concrete subclass InputStreamReader converts bytes to characters.
// To create a character-bases stream, br, that is linked to the console via System.in: BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
// read() can be used to read a character from a BufferedReader: int read() throws IOException.
// Each time read() is called, it reads a character from the input stream and returns it as integer value.
// Value -1 is returned when an attempt is made to read at the end of the stream.
//
// Note that System.in is line buffered by default. Meaning that no input is passed to the program until ENTER is pressed.

// Reading Strings
// BufferedReader class readLine() method is used to read a string from the keyboard: String readLine() throws IOException

public class Main {

    public static void main(String[] args) throws IOException {

//        // **********************
//        // Example to read characters from console
//        // **********************
//        System.out.println("Reading characters from console, with BufferedReader");
//        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // create a BufferedReader using System.in
//        System.out.println("Enter characters, 'q' to quit.");
//        do {
//            c = (char) br.read(); // read() will return an int value of the character, casting it to chart to display as character
//            System.out.print(c); // display character
//        } while(c != 'q'); // upon inputting q, quite loop
//        System.out.println();
////        // alternative to see integer input
////        System.out.println("Enter characters, 'q' to quit.");
////        do {
////            System.out.print(br.read());
////        } while(br.read() != 113);
////        System.out.println();
//
//        // **********************
//        // Example to read a string from console
//        // **********************
//        System.out.println("Reading a string from console, with BufferedReader");
//        String userinput;
//        System.out.println("Enter some lines of text");
//        System.out.println("Enter 'stop' to quit");
//        do {
//            userinput = br.readLine(); // using the BufferedReader (from the previous example) in combination with readLine, to read the input as a string
//            System.out.println(userinput); // display the string
//        } while(!userinput.equals("stop")); // upon inputting 'stop', quit the loop
//        System.out.println();

        // **********************
        // Example of a tiny text editor
        // **********************
        // Creating an array of String objects, reading up to 50 lines of text into the array, then displaying them.
        String userinput2[] = new String[50]; // creating an array of String objects
        System.out.println("Enter some lines of text");
        System.out.println("Enter 'stop' to quit");
        for (int i=0; i<50; i++) { // asking 50 times for a string input or breaking earlier upon input 'stop'
            userinput2[i] = br.readLine();
            if (userinput2[i].equals("stop")) break;
        }
        System.out.println("Here is your input:");
        for (int i=0; i<50; i++) { // looping through the array and displaying each string, until hitting the end or 'stop'
            if (userinput2[i].equals("stop")) break;
            System.out.println(userinput2[i]);
        }

    }
}