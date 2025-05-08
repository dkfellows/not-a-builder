# not-a-builder
An experiment in not using the Builder pattern.

There are two classes of interest:

* [Thing](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/Thing.java)
* [UseThing](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/UseThing.java)
* [TestThing](https://github.com/dkfellows/not-a-builder/blob/main/src/test/java/com/github/dkfellows/notabuilder/TestThing.java)

## UseThing and TestThing - How this is used

These demonstrate the use of the not-a-builder pattern I'm trying:
```java
var thing = new Thing(
    bar(123),
    quux("hello world"));
```
instead of needing to do something like:
```java
var thing = new Thing.Builder()
    .bar(123)
    .quux("hello world")
    .build();
```
All there is apart from that is the extra:
```java
import static com.github.dkfellows.notabuilder.Thing.Args.*;
```
at the top of the file.

## Thing - How this works
The `bar` and `quux` calls generate wrapped objects (instances of `Thing.Args`, which is a sum type of the possible argument types), and they can be passed to a variadic constructor on `Thing`. That then extracts the values (simple loop + pattern-matching switch) and sets the real fields appropriately.

```java
for (var arg: args) {
    switch (arg) {
    case Foo f -> foo = f;
    case Bar b -> bar = b;
    case Grill g -> grill = g;
    case Quux q -> quux = q;
    }
}
```

It's a bit messier than that because I'm using an outer record type (hence the `Thing.Arguments` class and the `private Thing(Arguments)` constructor, all of which looks rather Builder-y on the inside), but for a conventional class all that machinery could be in the sole constructor.

# Using an outside class: Thing2
This is basically the same, but with the outer entity being a standard class. That has the advantage of locking more of the machinery inside a single constructor.

* [Thing2](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/Thing2.java)
* [UseThing2](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/UseThing2.java) (very similar to with the version with records)
* [TestThing2](https://github.com/dkfellows/not-a-builder/blob/main/src/test/java/com/github/dkfellows/notabuilder/TestThing2.java)

```java
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
```
