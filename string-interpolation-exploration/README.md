## string interpolation compiler transformation

Looking at the compilation output of StringInterpolationTransformation in [compile.out](transformation/compile.out):

the compiler transforms
```scala
noop"this $a that"
```
to (line 40)
```scala
StringContext.apply("this ", " that")
```
then applies the following implicit conversion (line 2)
```scala
applied implicit conversion from StringContext to ?{def noop: ?} = implicit def Noop(sc: StringContext): StringInterpolationTransformation.Noop
```
to arrive at (line 40)
```scala
Noop(StringContext.apply("this ", " that")).noop(α)
```

## quick benchmarking bit

[bench.md](bench/target/scala-2.11/tut/bench.md)
