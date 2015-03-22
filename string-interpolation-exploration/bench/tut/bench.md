```tut
val th = ichi.bench.Thyme.warmedBench(verbose = print)

val β = "b"

th.pbench("a" + β)

th.pbench(s"a $β")

th.pbench("a %s".format(β))

val (a, b, c, d, e, f, g, h, i, j, k, l, m) = ("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m")

val (n, o, p, q, r, s, t, u, v, w, x, y, z) = ("n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")

th.pbench("a" + "b" + "c" + "d" + "e" + "f" + "g" + "h" + "i" + "j" + "k" + "l" + "m" +
  "n" + "o" + "p" + "q" + "r" + "s" + "t" + "u" + "v" + "w" + "x" + "y" + "z")

th.pbench(s"$a $b $c $d $e $f $g $h $i $j $k $l $m $n $o $p $q $r $s $t $u $v $w $x $y $z")

th.pbench("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s"
  .format(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z))
```
