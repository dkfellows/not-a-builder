package com.github.dkfellows.notabuilder;
import static com.github.dkfellows.notabuilder.Thing.Args.*;

/** An example of using a {@link Thing}. */
public class UseThing {
    private UseThing(){}
    /**
     * This is callable.
     * @param args Ignored
     */
    public static void main(String... args) {
        var thing = new Thing(
            bar(123),
            quux("hello world"));

        System.out.println(thing);
    }
}
