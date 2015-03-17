
scalaVersion := "2.11.6"

// http://tpolecat.github.io/2014/04/11/scalac-flags.html
scalacOptions in (Compile, compile) ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import")

wartremoverErrors in (Compile, compile) ++= Warts.allBut(Wart.NoNeedForMonad)

resolvers += "Linter Repository" at "https://hairyfotr.github.io/linteRepo/releases"

addCompilerPlugin("com.foursquare.lint" %% "linter" % "0.1.7")

tutSettings

tutSourceDirectory := file("tut")

libraryDependencies ++= Seq(
  "org.spire-math" %% "spire" % "0.9.1",
  "com.propensive" %% "rapture-json-play" % "1.1.0",
  "com.lihaoyi" %% "utest" % "0.3.1" % "test")

testFrameworks += new TestFramework("utest.runner.Framework")
