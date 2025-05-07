package com.github.dkfellows.notabuilder;
import static com.github.dkfellows.notabuilder.Thing.Args.*;

public class UseThing {
    public static void main(String... args) {
        var thing = new Thing(
            bar(123),
            quux("hello world"));

        System.out.println(thing);
    }
}
