package com.github.dkfellows.notabuilder;

public record Thing(int foo, int bar, double grill, String quux) {
    private Thing(Arguments args) {
        this(args.foo.foo(), args.bar.bar(), args.grill.grill(), args.quux.quux());
    }
    private static final Foo DEFAULT_FOO = new Foo(0);
    private static final Bar DEFAULT_BAR = new Bar(0);
    private static final Grill DEFAULT_GRILL = new Grill(0.0);
    private static final Quux DEFAULT_QUUX = new Quux("");

    private static final class Arguments {
        Foo foo = DEFAULT_FOO;
        Bar bar = DEFAULT_BAR;
        Grill grill = DEFAULT_GRILL;
        Quux quux = DEFAULT_QUUX;

        Arguments(Args[] args) {
            for (var arg: args) {
                switch (arg) {
                case Foo f -> foo = f;
                case Bar b -> bar = b;
                case Grill g -> grill = g;
                case Quux q -> quux = q;
                }
            }
        }
    }
    public Thing(Args... args) {
        this(new Arguments(args));
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
