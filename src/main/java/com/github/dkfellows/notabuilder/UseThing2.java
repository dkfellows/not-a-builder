package com.github.dkfellows.notabuilder;
import static com.github.dkfellows.notabuilder.Thing2.Args.*;

public class UseThing2 {
    public static void main(String... args) {
        var thing = new Thing2(
            bar(123),
            quux("hello world"));

        System.out.println(thing);
    }
}
