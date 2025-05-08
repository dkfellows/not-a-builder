package com.github.dkfellows.notabuilder;
import static com.github.dkfellows.notabuilder.Thing2.Args.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestThing2 {
    @Test
    public void testThing2() {
        var thing2 = new Thing2(
            bar(123),
            foo(321),
            quux("hello world"));
        System.out.println(thing2);
        assertEquals(123, thing2.getBar());
    }
}
