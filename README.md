# not-a-builder
An experiment in not using the Builder pattern.

There are two classes of interest:

* [Thing](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/Thing.java)
* [UseThing](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/UseThing.java)

## UseThing - How this is used

This demonstrates the use of the not-a-builder pattern I'm trying:
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

It's a bit messier than that because I'm using an outer record type (hence the `Thing.Arguments` class and the `private Thing(Arguments)` constructor), but for a conventional class all that machinery could be in the sole constructor.

Maybe I'll do an example with a standard outer class sometime; it makes some bits clearer and others less clear.
