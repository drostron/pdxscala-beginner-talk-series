object StringInterpolationTransformation {

  implicit class Noop(val sc: StringContext) extends AnyVal {
    def noop(args: String*): String = sc.parts.zipAll(args, "", "").map { case (i, j) => i + j }.mkString
  }

  val α = "and"

  val i = noop"this $α that"

}
