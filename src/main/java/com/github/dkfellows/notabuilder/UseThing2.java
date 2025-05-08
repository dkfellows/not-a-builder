package com.github.dkfellows.notabuilder;
import static com.github.dkfellows.notabuilder.Thing2.Args.*;

/** An example of using a {@link Thing2}. */
public class UseThing2 {
    private UseThing2(){}
    /**
     * This is callable.
     * @param args Ignored
     */
    public static void main(String... args) {
        var thing = new Thing2(
            bar(123),
            quux("hello world"));

        System.out.println(thing);
    }
}
