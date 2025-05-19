package com.github.dkfellows.notabuilder;

/**
 * A class that has four fields: foo, bar, grill and quux.
 */
public class ThingClass {
    private final int foo;
    private final int bar;
    private final double grill;
    private final String quux;

    /**
     * Make an instance of the class. 
     * @param args The labelled non-default arguments to pass.
     * @see ThingClass.Args#foo(int)
     * @see ThingClass.Args#bar(int)
     * @see ThingClass.Args#grill(double)
     * @see ThingClass.Args#quux(String)
     */
    public ThingClass(Args... args) {
        // Defaults
        var foo = 0;
        var bar = 0;
        var grill = 0.0;
        var quux = "";

        // Extract the args
        for (var arg: args) {
            switch (arg) {
            case Foo f -> foo = f.foo();
            case Bar b -> bar = b.bar();
            case Grill g -> grill = g.grill();
            case Quux q -> quux = q.quux();
            }
        }

        // Assign the fields (once, because final)
        this.foo = foo;
        this.bar = bar;
        this.grill = grill;
        this.quux = quux;
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
        return String.format("[foo=%s,bar=%s,grill=%s,quux=%s]", foo, bar, grill, quux);
    }

    /** Argument labeller. */
    public sealed interface Args permits Foo, Bar, Grill, Quux {
        /**
         * Label a value as a {@link ThingClass#getFoo() foo}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args foo(int value) {
            return new Foo(value);
        }
        /**
         * Label a value as a {@link ThingClass#getBar() bar}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args bar(int value) {
            return new Bar(value);
        }
        /**
         * Label a value as a {@link ThingClass#getGrill() grill}.
         * @param value The value to label.
         * @return The labelled value.
         */
        public static Args grill(double value) {
            return new Grill(value);
        }
        /**
         * Label a value as a {@link ThingClass#getQuux() quux}.
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
