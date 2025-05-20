# not-a-builder
An experiment in *not* using the Builder pattern.

There are two classes of interest:

* [ThingClass](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/ThingClass.java)
* [ThingRecord](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/ThingRecord.java)

Showing how these are used:

* [UseThingClass](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/uses/UseThingClass.java)
* [UseThingRecord](https://github.com/dkfellows/not-a-builder/blob/main/src/main/java/com/github/dkfellows/notabuilder/uses/UseThingRecord.java)
* [TestThingClass](https://github.com/dkfellows/not-a-builder/blob/main/src/test/java/com/github/dkfellows/notabuilder/TestThingClass.java)
* [TestThingRecord](https://github.com/dkfellows/not-a-builder/blob/main/src/test/java/com/github/dkfellows/notabuilder/TestThingRecord.java)

> [!NOTE]
> I've put some [javadocs](https://dkfellows.github.io/not-a-builder/apidocs/) up too, not that this example is intended to ever be an API that is used directly. May it inspire, but not be built upon itself.

## UseThingClass and TestThingClass - How this is used

These demonstrate the use of the not-a-builder pattern I'm trying:
```java
var thing = new ThingClass(
    bar(123),
    quux("hello world"));
```
instead of needing to do something like:
```java
var thing = new ThingClass.Builder()
    .bar(123)
    .quux("hello world")
    .build();
```
All there is apart from that is the extra:
```java
import static com.github.dkfellows.notabuilder.ThingClass.Args.*;
```
at the top of the file.

The important thing here is that we are making the instance by calling a constructor; it's obvious, patently right there.

The code relating to making a record, `ThingRecord`, is just the same, except for different class names. (Once made, things are slightly different too, but that's just as expected.)

## ThingClass - How this works
The `bar` and `quux` calls generate wrapped objects (instances of `ThingClass.Args`, which is a sum type of the possible argument types), and they can be passed to a variadic constructor on `ThingClass`. That then extracts the values (simple loop + pattern-matching switch) and sets the real fields appropriately.

```java
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
```

It's a bit messier than that for `ThingRecord` because I'm using an outer record type which requires that all fields are assigned by a call to the canonical constructor as the first statement. This requires the use of a static method to do the extraction, and needs the default values to be wrapped instances.

```java
private static <T extends Args> T select(Args[] args, T defaultValue) {
    T val = defaultValue;
    for (var arg: args) {
        if (arg.getClass() == val.getClass()) {
            @SuppressWarnings("unchecked")
            var thisVal = (T) arg;
            val = thisVal;
        }
    }
    return val;
}
```

The unchecked cast is actually safe here (under a trivial non-null assumption), but the language type logic doesn't do that sort of runtime type constraint so we need an unchecked cast anyway.
