package com.pbe;

// Rewritten version of MyClassWithoutThis, now with using this()
// In this version, the only constructor that actually assigns values to a and b is the first constructor: MyClassWithThis(int c, int d)
// The other two constructors invoke that constructor through this()
class MyClassWithThis {
    int a;
    int b;

    MyClassWithThis(int c, int d) { // initialize a and b individually
        a = c;
        b = d;
    }

    MyClassWithThis(int c) { // initialize a and b to the same value
        this(c, c); // invoking MyClassWithThis(int c, int d) and passing c as both values
    }

    MyClassWithThis() { // give a and b default values of 0
        this(0); // invoking MyClassWithThis(int c) and passing 0 as value
    }
}