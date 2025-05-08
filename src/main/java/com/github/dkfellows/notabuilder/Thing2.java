package com.github.dkfellows.notabuilder;

/**
 * A class that has four fields: foo, bar, grill and quux.
 */
public class Thing2 {
    private final int foo;
    private final int bar;
    private final double grill;
    private final String quux;

    private static final Foo DEFAULT_FOO = new Foo(0);
    private static final Bar DEFAULT_BAR = new Bar(0);
    private static final Grill DEFAULT_GRILL = new Grill(0.0);
    private static final Quux DEFAULT_QUUX = new Quux("");

    /**
     * Make an instance of the class. 
     * @param args The labelled non-default arguments to pass.
     * @see Thing2.Args#foo(int)
     * @see Thing2.Args#bar(int)
     * @see Thing2.Args#grill(double)
     * @see Thing2.Args#quux(String)
     */
    public Thing2(Args... args) {
        var foo = DEFAULT_FOO;
        var bar = DEFAULT_BAR;
        var grill = DEFAULT_GRILL;
        var quux = DEFAULT_QUUX;

        for (var arg: args) {
            switch (arg) {
            case Foo f -> foo = f;
            case Bar b -> bar = b;
            case Grill g -> grill = g;
            case Quux q -> quux = q;
            }
        }

        this.foo = foo.foo();
        this.bar = bar.bar();
        this.grill = grill.grill();
        this.quux = quux.quux();
    }

    /** Get the foo.
     * @return The foo value. */
    public int getFoo() { return foo; }
    /** Get the bar.
     * @return The bar value. */
    public int getBar() { return bar; }
    /** Get the grill.
     * @return The grill value. */
    public double getGrill() { return grill; }
    /** Get the quux.
     * @return The quux value. */
    public String getQuux() { return quux; }
    /** @return The string rendering of the object. */
    @Override
    public String toString() {
        return STR."""
        [foo=\{ foo },bar=\{ bar },grill=\{ grill },quux=\{ quux }]
        """;
    }

    /** Argument labeller. */
    public sealed interface Args permits Foo, Bar, Grill, Quux {
        /**
         * Label a value as a {@link Thing2#getFoo() foo}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args foo(int value) {
            return new Foo(value);
        }
        /**
         * Label a value as a {@link Thing2#getBar() bar}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args bar(int value) {
            return new Bar(value);
        }
        /**
         * Label a value as a {@link Thing2#getGrill() grill}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args grill(double value) {
            return new Grill(value);
        }
        /**
         * Label a value as a {@link Thing2#getQuux() quux}.
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
