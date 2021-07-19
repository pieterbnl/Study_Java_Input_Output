package com.pbe;

import java.io.*;
import static java.lang.Math.*;

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

// Transient and volatile modifiers
// Two modifiers to handle somewhat specialized situations:
// 1. transient - the value of a variable that's declared transient need not persist when an object is stored.
// For example: class C { transient int a; int b;}
// When an object of type C is written to a persistent storage, contents of a will not be saved, but the contents of b will.
// 2. volatile - modifier volatile tells the compiler that the variable modified by volatile can be changed unexpectedly by others parts of the program
// This may be the case in multithreaded programs, where multiple threads can share the same variable.
// Each thread can keep its own, private copy of the shared variable.
// The master copy of the variable is updated at various times, for example when a synchronized method is entered.
// When only the master is relevant, volatile can be used to tell the compiler that it must always use the master copy of the volatile variable/keep others up-to-date with it.

// Using instanceof
// Sometimes it's useful to know the type of an object during runtime (for example in situations that involves casting).
// This can be obtained with the run-time operator instanceof.
// The general form of the instanceof operator: objref instanceof type
// With objref being a reference to an instance of a class and type being a class type.
// If objref is of the specified type or can be cast into the specified type, instanceof operator evaluates to true.
// Using the instanceof operator can be especially useful when writing generalized routines that operate on objects of a complex class hierarchy.

// Native methods
// The native keyword is used to declare native code methods (i.e. to call a subroutine that is written in a language other than Java).
// Once declared, these methods can be called from inside the Java program as any other Java method would be called.
// The mechanism to integrate native code with a Java program is called the Java Native Interface JNI)
// After declaring a native method (for example: public native int method();) a complex series of steps is required to link the native method with the Java code.
// See Java documentation.

// Static import
// The feature 'static import' expands the capabilities of the import keyword.
// By following import with the keyword static, an import statement can be used to import the static members of a class or interface.
// Using static import, it's possible to refer to static members directly by their names, without having to qualify them with the name of their class.
// This simplifies and shortens the syntax.
//
// There are two forms of the import static statement:
// 1. import static pkg.type-name.static-member-name;
// Type-name is the name of the class or interface that contains the desired static member. Its full package name is specified by pkg. The member by static-member-name.
// 2. import static pkg.type-name.*;
// Imports all static members of a given class or interface, which can be handy when many methods or fields defined by a class are used.
//
// It's possible to import both the static members of classes and interfaces defined by the Java API, as well as own created.
// Static import is to be used very sparingly.
// Only use it when requiring frequent access to static members from one or two classes.
// When overusing the static import feature, it can make your program unreadable and unmaintainable, polluting its namespace with all the static members that are imported.
// Especially over time it will be hard to know which class a static member comes from
// Importing all of the static members from a class can be particularly harmful to readability.
// When needing only one or two members, import them individually.
// Used appropriately, static import can improve a program's readability, by removing the boilerplate of repetition of class names.

// Invoking overloaded constructors through this()
// It's sometimes useful for one constructor to invoke another. This is accomplished by using another form of the 'this' keyword: this(arg-list)
// When this() is executed, the overloaded constructor that matches the parameter list specified by arg-list is executed first.
// Next, if there are any statements inside the original constructor, they are executed.
// The call to this() must be the first statement within the constructor!
//
// Invoking overloaded constructors through this() can prevent unnecessary duplication of code, which decreases the class loading time.
// Using this() can also help structure code when constructors contain a large amount of duplicate code.
// At the same time, constructors that call this() will execute a bit slower, because the call and return mechanism used when the second constructor is invoked adds overhead.
// This may be of relevance when a class is used to create a large number of objects, in the order of thousands.
// It will then be about comparing the benefits of faster load time vs. increased time for object creation.
// This() is most applicable to constructors that contain large amounts of initialization code, not those that just set the value of some fields.
//
// Restrictions with the use of this():
// 1. You cannot use any instance variable of the constructors call to this().
// 2. You cannot use super() and this() in the same constructor, because each must be the first statement in the constructor.

// Reading from a file
// In general, each read request made of a Reader causes a corresponding read request to be made of the underlying character or byte stream.
// You can use BufferedReader to read from a file whose read() operations may be costly.
// BufferedReader reads text from a character-input stream.
// It buffers characters, for efficient reading of characters, arrays, and lines.
// It's buffer size may be specified, or the default size may be used, which is typically large enough.
// BufferedReader can be wrapped around any Reader, such as FileReaders and InputStreamReaders.
// For example: BufferedReader someFile = new BufferedReader(new FileReader("some_file.txt"))
// And as try with resources solution: try(BufferedReader someFile = new BufferedReader(new FileReader("some_file.txt"))) { .. }
// Using BufferedReader will buffer the input from the specified file.
// Without buffering, each invocation of read() or readLine() could cause bytes to be read from the file,
// converted into characters, and then returned, which can be very inefficient.

// Scanner vs BufferedReader
// Scanner is used for parsing tokens from the contents of the stream while BufferedReader just reads the stream and does not do any special parsing.
// It's possible to pass a BufferedReader to a Scanner as the source of characters to parse.
// Because Scanner parses input data and BufferedReader reads sequence of characters, BufferedReader is faster than Scanner.
// By using a buffer, BufferedReader avoid physical disk operations, making reading files more efficient.
// BufferedReader has a larger buffer memory than Scanner (8192 chars vs 1024 chars).
// BufferedReader is synchronous. Scanner is not.
//
// Recommended:
// Use Scanner to parse a file. Use BufferedReader to read a file line by line.
// Parsing = interpreting the given input as tokens (parts), allowing to give back specific parts directly as int, string, decimal, etc. Hence all those nextXxx() methods in Scanner class.
// Reading = dumb streaming, keeps returning all characters.
// These characters will have to be manually inspected when needing to to match or compose something useful (if needed).

// FileWriter & BufferedWriter
// BufferedWriter writes text to a character-output stream, buffering characters,
// so as to provide for the efficient writing of single characters, arrays, and strings.
// FileWriter is a convenience class for writing character files. It can be wrapped in BufferedWriter.
// For example: BufferedWriter somefile = new BufferedWriter(new FileWriter("some_file.txt"))

// More on streams
// As mentioned earlier, Java performs I/O through Streams, defining 2 types:
// 1. Byte Stream : provides a convenient means for handling I/O of 8-bit bytes.
// 2. Character Stream : provides a convenient means for handling I/O of characters.
// Character stream uses Unicode and therefore can be internationalized.
//
// All byte stream classes descent from InputStream and OutputStream, handling various devices such as disk files, network connections, etc.
// I.e. InputStream/OutputStream are the superclasses of all byte streams classes representing an input/output stream of bytes.
// There are many byte stream classes and all can be used in much the same way, only differing in the way they are constructed.
// The most important ones:
//
// - FileInputStream/FileOutputStream (=reading/writing from a file)
//   Reads/writes bytes from/to a file in a file system. Meant for reading/writing streams of raw bytes such as image data.
//   For reading/writing streams of characters, consider using FileReader/FileWriter.
//
// - BufferedInputStream/BufferedOutputStream (=ensuring buffered reading/writing - of underlying stream)
//   Implements a buffered input/output stream, allowing an application to read/write bytes to the underlying input/output stream.
//   Without necessarily causing a call to the underlying system for each byte written.
//
// - DataInputStream/DataOutputStream (=reading/writing primitives as bytes  - of underlying stream)
//   Lets an application write primitive Java data types to an output stream in a portable way.
//   An application can then use a data input/output stream to read/write the data.
//
// - ObjectInputStream/ObjectOutputStream (=reading/writing objects of class as a single unit)
//   Note: the process of translating an object into a format that can be stored and recreated = serialization
//   Need to implement serialize interface.
//   T.B.D later
//
// - PrintStream
//   An output stream that adds functionality to another output stream, namely the ability to print representations of various data values conveniently.
//   Contains the print() and println() method.
//
// DataOutputStream can be used to wrap BufferedOutputStream, which can wrap FileOutputStream:
// DataOutputStream someFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("some_file.dat")))

public class Main {

    public static void main(String[] args) throws IOException {

//        // **********************
//        // Example to read characters from console
//        // **********************
//        System.out.println("Reading characters from console, with BufferedReader");
//        char c;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // create a BufferedReader using System.in
//        System.out.println("Enter characters, 'q' to quit.");
//        do {
//            c = (char) br.read(); // read() will return an int value of the character, casting it to chart to display as character
//            System.out.print(c); // display character
//        } while(c != 'q'); // upon inputting q, quite loop
//        System.out.println();
//
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
//
//        // **********************
//        // Example of a tiny text editor
//        // **********************
//        // Creating an array of String objects, reading up to 50 lines of text into the array, then displaying them.
//        String userinput2[] = new String[50]; // creating an array of String objects
//        System.out.println("Enter some lines of text");
//        System.out.println("Enter 'stop' to quit");
//        for (int i=0; i<50; i++) { // asking 50 times for a string input or breaking earlier upon input 'stop'
//            userinput2[i] = br.readLine();
//            if (userinput2[i].equals("stop")) break;
//        }
//        System.out.println("Here is your input:");
//        for (int i=0; i<50; i++) { // looping through the array and displaying each string, until hitting the end or 'stop'
//            if (userinput2[i].equals("stop")) break;
//            System.out.println(userinput2[i]);
//        }
//
//        // **********************
//        // Example of using a PrintWriter to handle console output
//        // **********************
//        // System.out is fine to write simple text output to the console.
//        // Use PrintWriter for real programs to easier internationalize: the process of designing an application,
//        // so that it can be adapted to various languages and regions without engineering changes.
//        System.out.println("Using PrintWriter to handle console output");
//        PrintWriter pw = new PrintWriter(System.out, true); // setting System.out as output stream and setting auto flushing on
//        pw.println("This is some text string"); // printing a string of text
//        int x = -5; // setting an integer variable with a value
//        pw.println(x); // printing an integer variable value
//
//        // **********************
//        // Displaying a text file: Showfile & ShowFile2
//        // **********************
//        // Requires a file to be specified in the directory of where the ShowFile class is placed.
//        // Then to be run via command line, for example: java ShowFile.java readme.txt
//        System.out.println("Run java ShowFile.java readme.txt to display a readme.txt filename");
//        System.out.println();
//
//        // **********************
//        // Writing to a file: CloseFile
//        // **********************
//        // Requires a source and destination file to be specified in the directory of where the CloseFile class is placed.
//        // Then to be run via command line, for example: java CopyFile.java filename1.txt filename2.txt
//
//        // **********************
//        // Writing to a file: CloseFile2
//        // **********************
//        // Demonstrating use of two resources being managed by a single try statement

        // **********************
        // Writing to a file: CloseFile2
        // **********************
        // Demonstrating use of two resources being managed by a single try statement
        System.out.println("Example of using two resources being managed by a single try statement");
        Instanceof_ClassA obA = new Instanceof_ClassA();
        Instanceof_ClassB obB = new Instanceof_ClassB();
        Instanceof_ClassC obC = new Instanceof_ClassC();
        Instanceof_ClassD obD = new Instanceof_ClassD();
        if(obA instanceof Instanceof_ClassA)
            System.out.println("obA is an instance of Instanceof_ClassA");
        if(obB instanceof Instanceof_ClassB)
            System.out.println("obB is an instance of Instanceof_ClassB");
        if(obC instanceof Instanceof_ClassC)
            System.out.println("obC is an instance of Instanceof_ClassC");
        if(obC instanceof Instanceof_ClassA)
            System.out.println("obC can be cast to Instanceof_ClassA");
        if(obA instanceof Instanceof_ClassC)
            System.out.println("obA can be cast to Instanceof_ClassC");

        System.out.println();

        Instanceof_ClassA ob; // to compare types of derived types

        ob = obD; // Instanceof_ClassA reference to obD
        System.out.println("ob now refers to obD");
        if (ob instanceof Instanceof_ClassD)
            System.out.println("ob is an instance of Instanceof_ClassD");
        System.out.println();

        ob = obC; // Instanceof_ClassA reference to obC
        System.out.println("ob now refers to obC");
        if (ob instanceof Instanceof_ClassD)
            System.out.println("ob can be cast to Instanceof_ClassD");
        else
            System.out.println("ob can't be cast to Instanceof_ClassD");
        if (ob instanceof Instanceof_ClassA)
            System.out.println("ob can be cast to Instanceof_ClassA");
        System.out.println();

        // all objects can be cast to Object
        if (obA instanceof Object)
            System.out.println("obA may be cast to Object");
        if (obB instanceof Object)
            System.out.println("obB may be cast to Object");
        if (obC instanceof Object)
            System.out.println("obC may be cast to Object");
        if (obD instanceof Object)
            System.out.println("obD may be cast to Object");
        System.out.println();

        // **********************
        // Example without and with static import
        // **********************
        System.out.println("Example without and with static import");

        // without static import it's required to call the sqrt and tan methods (which are static methods), via their class Math
        double num1= Math.sqrt(10.0);
        double num2= Math.tan(50);
        System.out.println("Square of 10 is: " + num1);
        System.out.println("Tan of 50 is: " + num2);

        // with static import
        // after having specified (see up in the class): import static java.lang.Math.*;
        // it's not longer required to specify the Math class to use the sqrt and tan method
        // making long calculations easier readable and saving typing
        double num3= sqrt(10.0); // require to specify the package name Math before the method sqrt
        double num4= tan(50); // require to specify the package name Math before the method tan
        System.out.println("Square of 10 is: " + num1);
        System.out.println("Tan of 50 is: " + num2);

        // **********************
        // Example of (not) using this in constructors
        // **********************
        // See example classes MyClassWithoutThis and MyClassWithThis

    }
}