package com.pbe;

import java.io.*;

/** Study on Java I/O and related topics/keywords (try-with-resources, transient, volatile, instanceof, strictfp, assert)
 * Following Java The Complete Reference by Herbert Schildt i.c.w. (Udemy) Java programming masterclass for software developers Tim Buchalka.
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
//    (Internationalization the process of designing an application so that it can be adapted to various languages and regions without engineering changes.)
//    Java 1.0 only knew byte streams. Character streams where added in 1.1. Old code should be updated to take advantage of character streams.
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

// Console output
// Console output can be provided with print() and println(), defined by class PrintStream.
// The type of object referenced by System.out, which is a byte stream.
// PrintStream is an output stream derived from OutputStream, implementing the method write().
// Write() writes the byte specified by byteval and can be used to output to the console as well.

// PrintWriter class
// System.out is best to be used for debugging purposes or sample programs.
// For actual programs it's recommended to write to the console using PrintWriter stream.
// It's a character based class, making internationalizing a program easier.
// PrintWriter supports the print() and println() methods, which can be used the same way as with System.out
// The PrintWriter methods can call the object's toString() to display results.
// To write to the console: specify System.out for the output stream and automatic flushing.
// Flushing the stream means to clear the stream of any element that may be or maybe not inside the stream.
// It neither accepts any parameter nor returns any value.

// Reading and Writing files (introduction only)
// Two of the most often used stream classes which create byte streams linked to files:
// 1. FileInputStream(String fileName) throws FileNotFoundException
// 2. FileOutputStream(String fileName) throws FileNotFoundException
//
// To open a file, create an object of one of these classes by specifying the name of the file as an argument to the constructor.
// For an input stream, a FileNotFoundException is thrown if the specified file does not exist.
// For an output stream, a FileNotFoundException is thrown if the specified file cannot be opened or created.
// When an output file is opened, any pre-existing file by the same name is destroyed.
// Applications that use a security manager may throw a SecurityException if a security violation occurs when attempting to open a file.
//
// When being done with a file it must be closed by calling close(), which is implemented both by FileInputStream and FileOutputStream.
// Closing a file releases the system resources allocated to the file and prevents unused resources remaining allocated.
// Two basic approaches to close a file:
// 1. Call close() explicitly when a file is no longer needed - used approach by all Java versions prior to JDK7
// 2. Use the try-with-resources statement (added by JDK7), which automatically closes a file when it's no longer needed
//
// To read from a file, int read() throws IOException, that's defined within FileInputStream, can be used.
// Each time that it's called, it reads a single byte from the file and returns the byte as an integer value.
// It returns -1 when it has reached the end of athe stream.

// Automatically closing a file
// JDK7 added the feature Automatic Resource Management (ARM) that offers a way to manage resources -such as file streams- by automating the closing process.
// The ARM feature is based on an expanded version of the try statement.
// The advantage of ARM is that it prevents a resource from inadvertently not being released after it's no longer needed, which potentially results in memory leaks.
// ARM's general form: try (resource-specification) { // use the resource }
// Resource-specification is a statement that declares and initializes a resource.
// It consists of a variable declaration in which the variable is initialized with a reference to the object being managed.
// When the try block ends, the resource is automatically released.
// In case of a file this means that it's automatically closed, without requiring a call to close() explicitly.
// This form of try -which can also include catch and finally clauses- is called the 'try-with-resources' statement.
// Try-with-resources can only be used with resources that implement the (java.lang) AutoCloseable interface which defines close().
// AutoClosable in turn is inherited by the (java.io) Closeable interface.
// Both interfaces are implemented by the stream classes, which makes that try-with-resources can be used when working with (file) streams.
//
// It's possible to manage more than one resource within a single try statement, by separating each resource specification with a semicolon.
// By using using the try with resources statement in combination with managing multiple resources in a single try statement, this greatly streamlines the code.
// Also, an exception in a try block may lead to another exception (occurring when the resource is closed in the finally clause).
// Normally, the original exception is lost. But with try-with-resources, it's suppressed and can be obtained by using getSuppressed() as defined by Throwable.

public class Main {

    public static void main(String[] args) throws IOException {

        // **********************
        // Example to read characters from console
        // **********************
        System.out.println("Reading characters from console, with BufferedReader");
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // create a BufferedReader using System.in
        System.out.println("Enter characters, 'q' to quit.");
        do {
            c = (char) br.read(); // read() will return an int value of the character, casting it to chart to display as character
            System.out.print(c); // display character
        } while(c != 'q'); // upon inputting q, quite loop
        System.out.println();

//        // alternative to see integer input
//        System.out.println("Enter characters, 'q' to quit.");
//        do {
//            System.out.print(br.read());
//        } while(br.read() != 113);
//        System.out.println();

        // **********************
        // Example to read a string from console
        // **********************
        System.out.println("Reading a string from console, with BufferedReader");
        String userinput;
        System.out.println("Enter some lines of text");
        System.out.println("Enter 'stop' to quit");
        do {
            userinput = br.readLine(); // using the BufferedReader (from the previous example) in combination with readLine, to read the input as a string
            System.out.println(userinput); // display the string
        } while(!userinput.equals("stop")); // upon inputting 'stop', quit the loop
        System.out.println();

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

        // **********************
        // Example of using a PrintWriter to handle console output
        // **********************
        // System.out is fine to write simple text output to the console.
        // Use PrintWriter for real programs to easier internationalize: the process of designing an application,
        // so that it can be adapted to various languages and regions without engineering changes.
        System.out.println("Using PrintWriter to handle console output");
        PrintWriter pw = new PrintWriter(System.out, true); // setting System.out as output stream and setting auto flushing on
        pw.println("This is some text string"); // printing a string of text
        int x = -5; // setting an integer variable with a value
        pw.println(x); // printing an integer variable value

        // **********************
        // Displaying a text file: Showfile & ShowFile2
        // **********************
        // Requires a file to be specified in the directory of where the ShowFile class is placed.
        // Then to be run via command line, for example: java ShowFile.java readme.txt
        System.out.println("Run java ShowFile.java readme.txt to display a readme.txt filename");
        System.out.println();

        // **********************
        // Writing to a file: CloseFile
        // **********************
        // Requires a source and destination file to be specified in the directory of where the CloseFile class is placed.
        // Then to be run via command line, for example: java CopyFile.java filename1.txt filename2.txt
        System.out.println("Writing to a file");

        // **********************
        // Writing to a file: CloseFile2
        // **********************
        // Demonstrating use of two resources being managed by a single try statement


    }
}