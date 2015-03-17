object oz {

  val vowels: List[Char] = "aeiou".flatMap(i => List(i.toUpper, i)).toCharArray.toList

  // simplified
  def pigLatinise(v: String): String = v.split(' ').map { word =>
    word.toList match {
      case h :: _ if vowels.contains(h) => word + "way"
      case h :: _ =>
        val (i, j) = word.splitAt(word.prefixLength(!vowels.contains(_)))
        j.mkString + i.mkString + "ay"
      case Nil => word
    }
  }.mkString(" ")

  def gibberishise(v: String): String = ???

}
