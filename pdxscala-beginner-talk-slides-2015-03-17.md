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

```tut:silent
import scala.math.{ E => _, _ }
```

```tut:nofail
Pi
E
```

```tut:silent
import scala.math._
```

```tut
E
```

---

## example

_setting the stage_

```tut:invisible
import oz._
```

```tut:silent
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

```tut:nofail
import Translations._
translate("language games")
```

```tut:invisible
implicit object Gibberish
```

```tut
import Translations.{ Gibberish => _, _ }
translate("language games")
```

---

## import renaming

```tut:silent
import java.util.{ Map => JMap, HashMap => JHashMap }
```

```tut
val jMap: JMap[String, Double] = new JHashMap
```

---

## altogether now

```tut:silent
import java.util.{ Map => JMap, List => _, _ }
import scala.collection.concurrent.{ Map => _, _ }
```

```tut
val map = Map("a" -> 2, "b" -> 1)
val jMap: JMap[String, Double] = new HashMap
jMap.put("α", 3.7)
val trieMap = TrieMap("β" -> 11.9)
```

---

## by-value and by-name parameters

```tut:silent
def i[T](v: T): List[T] = List(v, v, v)
def j[T](v: => T): List[T] = List(v, v, v)
```

```tut
i { println("."); "by-value" }
j { println("."); "by-name" }
```

---

## example

```tut:silent
@annotation.tailrec
def nTimes[T](n: Int)(op: => T): Unit = if(n > 0) { op; nTimes(n-1)(op) }
```

```tut
var i = 0
nTimes(3)(i += 1)
i
```

---

## example

```tut:silent
def ɟᴉ[T](pred: Boolean)(t: => T)(f: => T): T = pred match {
  case true => t
  case false => f
}
```

```tut
ɟᴉ(true) { println("T"); 3 } { println("F"); 7 }
ɟᴉ(false) { println("T"); 11 } { println("F"); 13 }
```

---

further information:

http://tpolecat.github.io/2014/06/26/call-by-name.html

---

## string interpolation : out of the box

String Interpolator
```tut
s"π: $Pi, cos(π): ${cos(Pi)}"
```

Format Interpolator, _java.util.Formatter_
```tut
f"Aryabhata's π $Pi%.4f"
```

Raw Interpolator
```tut
raw"Look\tno\nEscaping"
```

---

## string interpolation : custom

```tut:silent
implicit class PigLatinHelper(val sc: StringContext) extends AnyVal {
  def pl(args: String*): String = pigLatinise(
    sc.parts.zipAll(args, "", "").map { case (i, j) => i + j }.mkString)
}
```

```tut
val α = "and"
pl"this $α that"
```

--

compiler rewrites to:
```tut
new StringContext("this ", " that").pl(α)
```

---

## examples in the wild : [spire](https://github.com/non/spire)

```tut:silent
import spire.syntax.literals._
```

```tut
b"1"
ul"1"
r"1/7"
poly"x^2 - 3x + 7"
```

---

## examples in the wild : [spire](https://github.com/non/spire)

```tut:silent
import spire.syntax.literals.radix._
```

```tut
x2"1011"
x16"def"
```
```tut:silent
import spire.implicits._, spire.syntax.literals.us._
```

```tut
"1,024"
big"2" ** 200
```

???

also si and eu literals

---

## examples in the wild : [rapture-json](https://github.com/propensive/rapture-json)

```tut:silent
import rapture.json._, jsonBackends.play._
```

```tut
val i = 3
val j = json"""{ "i" : $i }"""
val json"""{ "i" : $v }""" = j
v.as[Option[Int]]
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

## Adjourn to Pub

Life of Riley

.pub-map[]
