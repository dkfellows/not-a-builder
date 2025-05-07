package com.github.dkfellows.notabuilder;
import static com.github.dkfellows.notabuilder.Thing.Args.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestThing {
    @Test
    public void testThing() {
        var thing = new Thing(
            Bar(123),
            Foo(321),
            Quux("hello world"));
        System.out.println(thing);
        assertEquals(123, thing.bar());
    }
}
