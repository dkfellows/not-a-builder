package com.github.dkfellows.notabuilder;
import org.junit.jupiter.api.Test;
public class TestThing {
    public static void main(String... args) {
        var thing = new Thing(
            new Bar(123),
            new Quux("hello world"));

        System.out.println(thing);
    }

    @Test
    public void testThing() {
        var thing = new Thing(
            new Bar(123),
            new Quux("hello world"));
        assert thing.bar() == 123;
    }
}
