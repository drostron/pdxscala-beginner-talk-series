```scala
scala> val th = ichi.bench.Thyme.warmedBench(verbose = print)
Creating Thyme instances and warming busywork methods...done in 3.87 s
Warming up benchmarking...done in 20.91 s
th: ichi.bench.Thyme = ichi.bench.Thyme@8144d50

scala> val β = "b"
β: String = b

scala> th.pbench("a" + β)
Benchmark (167772140 calls in 6.948 s)
  Time:    38.30 ns   95% CI 36.31 ns - 40.28 ns   (n=20)
  Garbage: 1.848 ns   (n=71 sweeps measured)
res0: String = ab

scala> th.pbench(s"a $β")
Benchmark (20971500 calls in 2.310 s)
  Time:    88.36 ns   95% CI 83.26 ns - 93.46 ns   (n=20)
  Garbage: 3.815 ns   (n=14 sweeps measured)
res1: String = a b

scala> th.pbench("a %s".format(β))
Benchmark (2621420 calls in 2.364 s)
  Time:    788.3 ns   95% CI 746.3 ns - 830.3 ns   (n=20)
  Garbage: 22.13 ns   (n=9 sweeps measured)
res2: String = a b

scala> val (a, b, c, d, e, f, g, h, i, j, k, l, m) = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m")
a: String = a
b: String = b
c: String = c
d: String = d
e: String = e
f: String = f
g: String = g
h: String = h
i: String = i
j: String = j
k: String = k
l: String = l
m: String = m

scala> val (n, o, p, q, r, s, t, u, v, w, x, y, z) = ("n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
n: String = n
o: String = o
p: String = p
q: String = q
r: String = r
s: String = s
t: String = t
u: String = u
v: String = v
w: String = w
x: String = x
y: String = y
z: String = z

scala> th.pbench("a" + "b" + "c" + "d" + "e" + "f" + "g" + "h" + "i" + "j" + "k" + "l" + "m" +
     |   "n" + "o" + "p" + "q" + "r" + "s" + "t" + "u" + "v" + "w" + "x" + "y" + "z")
Benchmark (671088620 calls in 3.846 s)
  Time:    5.742 ns   95% CI 5.526 ns - 5.957 ns   (n=20)
res3: String = abcdefghijklmnopqrstuvwxyz

scala> th.pbench(s"$a $b $c $d $e $f $g $h $i $j $k $l $m $n $o $p $q $r $s $t $u $v $w $x $y $z")
Benchmark (5242860 calls in 5.267 s)
  Time:    916.5 ns   95% CI 893.1 ns - 939.9 ns   (n=20)
  Garbage: 14.88 ns   (n=14 sweeps measured)
res4: String = a b c d e f g h i j k l m n o p q r s t u v w x y z

scala> th.pbench("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s"
     |   .format(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z))
Benchmark (327660 calls in 5.426 s)
  Time:    15.31 us   95% CI 14.81 us - 15.80 us   (n=19)
  Garbage: 305.2 ns   (n=11 sweeps measured)
res5: String = a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z
```
