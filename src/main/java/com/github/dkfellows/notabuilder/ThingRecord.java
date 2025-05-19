package com.github.dkfellows.notabuilder;

/**
 * A record that has four fields.
 * @param foo The foo field.
 * @param bar The bar field.
 * @param grill The grill field.
 * @param quux The quux field.
 */
public record ThingRecord(int foo, int bar, double grill, String quux) {
    private static final Foo DEFAULT_FOO = new Foo(0);
    private static final Bar DEFAULT_BAR = new Bar(0);
    private static final Grill DEFAULT_GRILL = new Grill(0.0);
    private static final Quux DEFAULT_QUUX = new Quux("");

    /** Helper because the canonical constructor must be called as the first statement.
     * @param <T> The type of argument being extracted.
     * @param args The argument list to extract from.
     * @param defaultValue The default for the argument being extracted.
     *      Note that <em>this also tells the runtime code what argument to extract</em>.
     * @return The extracted argument, if present, or a default.
     */
    @SuppressWarnings("unchecked")
    private static <T extends Args> T select(Args[] args, T defaultValue) {
        T val = defaultValue;
        for (var arg: args) {
            if (arg.getClass() == val.getClass()) {
                val = (T) arg;
            }
        }
        return val;
    }

    /**
     * Make an instance of the record. 
     * @param args The labelled non-default arguments to pass.
     * @see ThingRecord.Args#foo(int)
     * @see ThingRecord.Args#bar(int)
     * @see ThingRecord.Args#grill(double)
     * @see ThingRecord.Args#quux(String)
     */
    public ThingRecord(Args... args) {
        this(select(args, DEFAULT_FOO).foo(),
            select(args, DEFAULT_BAR).bar(),
            select(args, DEFAULT_GRILL).grill(),
            select(args, DEFAULT_QUUX).quux());
    }

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
