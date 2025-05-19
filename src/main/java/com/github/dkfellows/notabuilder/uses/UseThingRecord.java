package com.github.dkfellows.notabuilder.uses;
import static com.github.dkfellows.notabuilder.ThingRecord.Args.*;

import com.github.dkfellows.notabuilder.ThingRecord;

/** An example of using a {@link ThingRecord}. */
public class UseThingRecord {
    private UseThingRecord(){}
    /**
     * This is callable.
     * @param args Ignored
     */
    public static void main(String... args) {
        var thing = new ThingRecord(
            bar(123),
            quux("hello world"));

        System.out.println(thing);
    }
}
