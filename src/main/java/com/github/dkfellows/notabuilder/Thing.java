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

        Arguments(ThingArgs[] args) {
            for (ThingArgs arg: args) {
                switch (arg) {
                case Foo f -> foo = f;
                case Bar b -> bar = b;
                case Grill g -> grill = g;
                case Quux q -> quux = q;
                }
            }
        }
    }
    public Thing(ThingArgs... args) {
        this(new Arguments(args));
    }
}

sealed interface ThingArgs permits Foo, Bar, Grill, Quux {}

record Foo(int foo) implements ThingArgs {
}

record Bar(int bar) implements ThingArgs {
}

record Grill(double grill) implements ThingArgs {
}

record Quux(String quux) implements ThingArgs {
}
