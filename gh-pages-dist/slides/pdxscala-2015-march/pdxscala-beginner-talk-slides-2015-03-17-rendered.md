# PDXScala

March 17, 2015

.agenda[
- Beginner Talk Series
- Office Hours
- Adjourn to Pub
]

.sponsor[
meeting space sponsor

![Urban Airship](http://urbanairship.com/images/backgrounds/logo.png)
]

.sponsor[
pizza sponsor

![Janrain](http://janrain.com/wp-content/themes/janrain/library/images/janrain-logo@2x.png)
]

---

# PDXScala Beginner Talk Series

.talk-topics[
import hiding, import renaming

by-value and by-name parameters

string interpolation
]

[Dave Rostron](http://github.com/drostron) — [@yastero](http://twitter.com/yastero)

March 17, 2015

---

## import hiding

```scala
import scala.math.{ E => _, _ }
```

```scala
scala> Pi
res0: Double = 3.141592653589793

scala> E
<console>:12: error: not found: value E
              E
              ^
```

```scala
import scala.math._
```

```scala
scala> E
res2: Double = 2.718281828459045
```

---

## example

_setting the stage_




```scala
trait Translation[T] { def translate(v: String): String }

def translate[T : Translation](v: String) = implicitly[Translation[T]].translate(v)

object Translations {
  implicit object PigLatin extends Translation[String] {
    def translate(v: String): String = pigLatinise(v)
  }
  implicit object Gibberish extends Translation[String] {
   def translate(v: String): String = gibberishise(v)
  }
}
```

---

## example

```scala
scala> import Translations._
import Translations._

scala> translate("language games")
<console>:24: error: ambiguous implicit values:
 both object PigLatin in object Translations of type Translations.PigLatin.type
 and object Gibberish in object Translations of type Translations.Gibberish.type
 match expected type Translation[T]
              translate("language games")
                       ^
```




```scala
scala> import Translations.{ Gibberish => _, _ }
import Translations.{Gibberish=>_, _}

scala> translate("language games")
res4: String = anguagela amesga
```

---

## import renaming

```scala
import java.util.{ Map => JMap, HashMap => JHashMap }
```

```scala
scala> val jMap: JMap[String, Double] = new JHashMap
jMap: java.util.Map[String,Double] = {}
```

---

## altogether now

```scala
import java.util.{ Map => JMap, List => _, _ }

import scala.collection.concurrent.{ Map => _, _ }
```

```scala
scala> val map = Map("a" -> 2, "b" -> 1)
map: scala.collection.immutable.Map[String,Int] = Map(a -> 2, b -> 1)

scala> val jMap: JMap[String, Double] = new HashMap
jMap: java.util.Map[String,Double] = {}

scala> jMap.put("α", 3.7)
res5: Double = 0.0

scala> val trieMap = TrieMap("β" -> 11.9)
trieMap: scala.collection.concurrent.TrieMap[String,Double] = TrieMap(β -> 11.9)
```

---

## by-value and by-name parameters

```scala
def i[T](v: T): List[T] = List(v, v, v)

def j[T](v: => T): List[T] = List(v, v, v)
```

```scala
scala> i { println("."); "by-value" }
.
res6: List[String] = List(by-value, by-value, by-value)

scala> j { println("."); "by-name" }
.
.
.
res7: List[String] = List(by-name, by-name, by-name)
```

---

## _example here_

---

further information:

http://tpolecat.github.io/2014/06/26/call-by-name.html

---

## string interpolation
- why?
- key features
- they're interesting due to?
- type safety, explain
- explain how they work
- quick macro explanation (don't dwell here), well, appears macros are not necessarily utilized?
- build up another example?
  - svg? visuals are fun
  - currencies
  - other units

---

## string interpolation (std)

String Interpolator
```scala
scala> s"π: $Pi, cos(π): ${cos(Pi)}"
res8: String = π: 3.141592653589793, cos(π): -1.0
```

Format Interpolator, _java.util.Formatter_
```scala
scala> f"Aryabhata's π $Pi%.4f"
res9: String = Aryabhata's π 3.1416
```

Raw Interpolator
```scala
scala> raw"Look\tno\nEscaping"
res10: String = Look\tno\nEscaping
```

---

## string interpolation (custom)

```scala
implicit class PigLatinHelper(val sc: StringContext) extends AnyVal {
  def pl(args: Any*): String = pigLatinise(
    sc.parts.zipAll(args, "", "").map { case (i, j) => i + j }.mkString)
}
```

```scala
scala> val α = "and"
α: String = and

scala> pl"this $α that"
res11: String = hista andwa hatta
```

---

## examples in the wild : [spire](https://github.com/non/spire)

```scala
import spire.syntax.literals._
```

```scala
scala> b"1"
res12: Byte = 1

scala> ub"1"
res13: spire.math.UByte = 1

scala> ui"1"
res14: spire.math.UInt = 1

scala> ul"1"
res15: spire.math.ULong = 1

scala> r"1/7"
res16: spire.math.Rational = 1/7

scala> poly"x^2 - 3x + 7"
res17: spire.math.Polynomial[spire.math.Rational] = (x^2 - 3x + 7)
```

---

## examples in the wild : [spire](https://github.com/non/spire)

```scala
import spire.syntax.literals.radix._
```

```scala
scala> x2"1011"
res18: Int = 11

scala> x16"def"
res19: Int = 3567
```
```scala
import spire.implicits._

import spire.syntax.literals.us._ // also si and eu
```

```scala
scala> i"1,024"
res20: Int = 1024

scala> big"2" ** 200
res21: BigInt = 1606938044258990275541962092341162602522202993782792835301376
```

---

## examples in the wild : [rapture-json](https://github.com/propensive/rapture-json)

```scala
import rapture.json._, jsonBackends.play._
```

```scala
scala> val i = 3
i: Int = 3

scala> json"""{ "i" : $i }"""
res22: rapture.json.Json = {"i":3}
```

---

further information:

http://docs.scala-lang.org/overviews/core/string-interpolation.html

---

Questions and/or Thoughts?

--

Thank you.

---

## future beginner talks

great low commitment opportunity to speak. please join us! let's learn together!

---

## Office Hours

---

## Adjourn to Pub

Life of Riley

<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d2795.2435141151022!2d-122.680932!3d45.525304999999996!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x54950a01f3c028fb%3A0x6aa7706ab3319d4e!2sLife+of+Riley+Inc!5e0!3m2!1sen!2sus!4v1426378182446" width="600" height="450" frameborder="0" style="border:0"></iframe>
