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

## string interpolation (custom)

```tut:silent
implicit class PigLatinHelper(val sc: StringContext) extends AnyVal {
  def pl(args: Any*): String = pigLatinise(
    sc.parts.zipAll(args, "", "").map { case (i, j) => i + j }.mkString)
}
```

```tut
val α = "and"
pl"this $α that"
```

---

## examples in the wild : [spire](https://github.com/non/spire)

```tut:silent
import spire.syntax.literals._
```

```tut
b"1"
ub"1"
ui"1"
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
import spire.implicits._
import spire.syntax.literals.us._ // also si and eu
```

```tut
i"1,024"
big"2" ** 200
```

---

## examples in the wild : [rapture-json](https://github.com/propensive/rapture-json)

```tut:silent
import rapture.json._, jsonBackends.play._
```

```tut
val i = 3
json"""{ "i" : $i }"""
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
