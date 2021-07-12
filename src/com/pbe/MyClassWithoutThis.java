package com.pbe;

// This class contains 3 constructors. Each initializes the values a and b.
// By using this(), it's possible to rewrite the class as shown in MyClassWithThis
class MyClassWithoutThis {
    int a;
    int b;

    MyClassWithoutThis(int c, int d) { // initialize a and b individually
        a = c;
        b = d;
    }

    MyClassWithoutThis(int c) { // initialize a and b to the same value
        a = c;
        b = c;
    }

    MyClassWithoutThis() { // give a and b default values of 0
        a = 0;
        b = 0;
    }
}