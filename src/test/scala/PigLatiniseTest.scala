import oz._
import utest._

object PigLatinSuite extends TestSuite {

  val tests = TestSuite {
    "pigLatinise" - {
      "words that begin with consonant sounds" - {
        assert(pigLatinise("pig") == "igpay")
        assert(pigLatinise("banana") == "ananabay")
        assert(pigLatinise("trash") == "ashtray")
        assert(pigLatinise("happy") == "appyhay")
        assert(pigLatinise("duck") == "uckday")
        assert(pigLatinise("glove") == "oveglay")
      }
      "words that begin with vowel sounds" - {
        assert(pigLatinise("egg") == "eggway")
        assert(pigLatinise("inbox") == "inboxway")
        assert(pigLatinise("eight") == "eightway")
      }
      "words that are empty" - {
        assert(pigLatinise("") == "")
      }
    }
  }

}
