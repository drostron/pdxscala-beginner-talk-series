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

## import renaming

```scala
import java.util.{ Map => JMap, HashMap => JHashMap }
```

```scala
scala> val jMap: JMap[String, Double] = new JHashMap
jMap: java.util.Map[String,Double] = {}
```

---

## import hiding

```scala
import scala.math.{ E => _, _ }
```

```scala
scala> Pi
res0: Double = 3.141592653589793

scala> E
<console>:13: error: not found: value E
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

def translate[T : Translation](v: String) =
  implicitly[Translation[T]].translate(v)

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
<console>:25: error: ambiguous implicit values:
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
res4: String = anguagelay amesgay
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
scala> i { println("☘"); "by-value" }
☘
res6: List[String] = List(by-value, by-value, by-value)

scala> j { println("☘"); "by-name" }
☘
☘
☘
res7: List[String] = List(by-name, by-name, by-name)
```

---

## example

```scala
@annotation.tailrec
def nTimes(n: Int)(op: => Unit): Unit = if(n > 0) { op; nTimes(n-1)(op) }
```

```scala
scala> var i = 0
i: Int = 0

scala> nTimes(3)(i += 1)

scala> i
res9: Int = 3
```

---

## example

```scala
def ɟᴉ[T](pred: Boolean)(t: => T)(f: => T): T = pred match {
  case true => t
  case false => f
}
```

```scala
scala> ɟᴉ(true) { println("T"); 3 } { println("F"); 7 }
T
res10: Int = 3

scala> ɟᴉ(false) { println("T"); 11 } { println("F"); 13 }
F
res11: Int = 13
```

---

further information:

http://tpolecat.github.io/2014/06/26/call-by-name.html

---

## string interpolation : out of the box

String Interpolator
```scala
scala> s"π: $Pi, cos(π): ${cos(Pi)}"
res12: String = π: 3.141592653589793, cos(π): -1.0
```

Format Interpolator, _java.util.Formatter_
```scala
scala> f"Aryabhata's π $Pi%.4f"
res13: String = Aryabhata's π 3.1416
```

Raw Interpolator
```scala
scala> raw"Look\tno\nEscaping"
res14: String = Look\tno\nEscaping
```

---

## string interpolation : custom

```scala
implicit class PigLatinHelper(val sc: StringContext) extends AnyVal {
  def pl(args: String*): String = pigLatinise(
    sc.parts.zipAll(args, "", "").map { case (i, j) => i + j }.mkString)
}
```

```scala
scala> val α = "banana"
α: String = banana

scala> pl"happy $α glove"
res15: String = appyhay ananabay oveglay
```

--

compiler rewrites to:
```scala
scala> new StringContext("happy ", " glove").pl(α)
res16: String = appyhay ananabay oveglay
```

---

## examples in the wild : [spire](https://github.com/non/spire)

```scala
import spire.syntax.literals._
```

```scala
scala> b"1"
res17: Byte = 1

scala> ul"1"
res18: spire.math.ULong = 1

scala> r"1/7"
res19: spire.math.Rational = 1/7

scala> poly"x^2 - 3x + 7"
res20: spire.math.Polynomial[spire.math.Rational] = (x^2 - 3x + 7)
```

---

## examples in the wild : [spire](https://github.com/non/spire)

```scala
import spire.syntax.literals.radix._
```

```scala
scala> x2"1011"
res21: Int = 11

scala> x16"def"
res22: Int = 3567
```
```scala
import spire.implicits._, spire.syntax.literals.us._
```

```scala
scala> i"1,024"
res23: Int = 1024

scala> big"2" ** 200
res24: BigInt = 1606938044258990275541962092341162602522202993782792835301376
```

???

also si and eu literals

---

## examples in the wild : [rapture-json](https://github.com/propensive/rapture-json)

```scala
import rapture.json._, jsonBackends.play._
```

```scala
scala> val i = 3
i: Int = 3

scala> val j = json"""{ "i" : $i }"""
j: rapture.json.Json = {"i":3}

scala> val json"""{ "i" : $v }""" = j
v: rapture.data.DataType[rapture.json.Json,rapture.data.DataAst] = 3

scala> v.as[Option[Int]]
res25: Option[Int] = Some(3)
```

???

further info on unapply:
- [SIP-11](https://docs.google.com/document/d/1NdxNxZYodPA-c4MLr33KzwzKFkzm9iW9POexT9PkJsU/edit?hl=en_US)
- [patternmatching and string interpolation : scala-internals discussion](https://groups.google.com/d/topic/scala-internals/AmZl7VqV_rk)

---

further information:

http://docs.scala-lang.org/overviews/core/string-interpolation.html

---

## Questions

---

## Thank you

---

## future beginner talks

great low commitment opportunity to speak. please join us! let's learn together!

---

## Office Hours

---

name: pub

## ☘ Adjourn to Pub ☘

Life of Riley

.pub-map[]
