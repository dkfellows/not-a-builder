package com.github.dkfellows.notabuilder;
import static com.github.dkfellows.notabuilder.ThingClass.Args.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestThingClass {
    @Test
    public void makeInstance() {
        var thing = new ThingClass(
            bar(123),
            foo(321),
            quux("hello world"));
        System.out.println(thing);
        assertEquals(123, thing.getBar());
    }
}
