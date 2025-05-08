package com.github.dkfellows.notabuilder;

public class Thing2 {
    private final int foo;
    private final int bar;
    private final double grill;
    private final String quux;

    private static final Foo DEFAULT_FOO = new Foo(0);
    private static final Bar DEFAULT_BAR = new Bar(0);
    private static final Grill DEFAULT_GRILL = new Grill(0.0);
    private static final Quux DEFAULT_QUUX = new Quux("");

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

    public int getFoo() { return foo; }
    public int getBar() { return bar; }
    public double getGrill() { return grill; }
    public String getQuux() { return quux; }
    public String toString() {
        return STR."""
        [foo=\{ foo },bar=\{ bar },grill=\{ grill },quux=\{ quux }]
        """;
    }

    public sealed interface Args permits Foo, Bar, Grill, Quux {
        public static Args foo(int value) {
            return new Foo(value);
        }
        public static Args bar(int value) {
            return new Bar(value);
        }
        public static Args grill(double value) {
            return new Grill(value);
        }
        public static Args quux(String value) {
            return new Quux(value);
        }
    }

    private record Foo(int foo) implements Args {}
    private record Bar(int bar) implements Args {}
    private record Grill(double grill) implements Args {}
    private record Quux(String quux) implements Args {}
}    
