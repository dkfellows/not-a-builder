package com.github.dkfellows.notabuilder.uses;
import static com.github.dkfellows.notabuilder.ThingClass.Args.*;

import com.github.dkfellows.notabuilder.ThingClass;

/** An example of using a {@link ThingClass}. */
public class UseThing2 {
    private UseThing2(){}
    /**
     * This is callable.
     * @param args Ignored
     */
    public static void main(String... args) {
        var thing = new ThingClass(
            bar(123),
            quux("hello world"));

        System.out.println(thing);
    }
}
