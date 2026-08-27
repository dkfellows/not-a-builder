package com.github.dkfellows.notabuilder;

/**
 * A record that has four fields.
 * @param foo The foo field.
 * @param bar The bar field.
 * @param grill The grill field.
 * @param quux The quux field.
 */
public record ThingRecord(int foo, int bar, double grill, String quux) {
    /**
     * Make an instance of the record.
     * @param args The labelled non-default arguments to pass.
     * @see ThingRecord.Args#foo(int)
     * @see ThingRecord.Args#bar(int)
     * @see ThingRecord.Args#grill(double)
     * @see ThingRecord.Args#quux(String)
     */
    public ThingRecord(Args... args) {
        // Defaults
        var foo = 0;
        var bar = 0;
        var grill = 0.0;
        var quux = "";

        // Extract the args by pattern matching
        for (var arg: args) {
            switch (arg) {
            case Foo(var val) -> foo = val;
            case Bar(var val) -> bar = val;
            case Grill(var val) -> grill = val;
            case Quux(var val) -> quux = val;
            }
        }

        // Pass to the primary, auto-generated constructor
        this(foo, bar, grill, quux);
    }

    /** @deprecated Use the other constructor instead. */
    @Deprecated(since = "0.1")
    public ThingRecord {}

    /** Argument labeller. */
    public sealed interface Args permits Foo, Bar, Grill, Quux {
        /**
         * Label a value as a {@link ThingRecord#foo()}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args foo(int value) {
            return new Foo(value);
        }

        /**
         * Label a value as a {@link ThingRecord#bar()}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args bar(int value) {
            return new Bar(value);
        }

        /**
         * Label a value as a {@link ThingRecord#grill()}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args grill(double value) {
            return new Grill(value);
        }

        /**
         * Label a value as a {@link ThingRecord#quux()}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args quux(String value) {
            return new Quux(value);
        }
    }

    private record Foo(int foo) implements Args {}
    private record Bar(int bar) implements Args {}
    private record Grill(double grill) implements Args {}
    private record Quux(String quux) implements Args {}
}
