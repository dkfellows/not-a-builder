package com.github.dkfellows.notabuilder;
import static com.github.dkfellows.notabuilder.Thing.*;

public class UseThing {
    public static void main(String... args) {
        var thing = new Thing(
            Bar(123),
            Quux("hello world"));

        System.out.println(thing);
    }
}
