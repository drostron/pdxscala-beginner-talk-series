# PDXScala Beginner Talks

https://gist.github.com/tpolecat/968b79265827d1431d0e

## todos
- make a pl"this and that" pig latin interpolator
- restructure directory layout
- revert screen saver changes
- add opening slide for thanks to sponsors, schedule, ...?
- before presentation: turn off notifications, close email/cal tabs

## notes
- open meeting with beginner talk, follow with questions, then be available for informal questions, adjourn to a pub for interested parties

## March 17, 2015
- import hiding, import renaming
- by-value, by-name
- string interpolation

## scala from scratch (scratch baking with scala, img opportunity)
- sbt basic (flat) and canonical layouts for single and multi-project
- plugins: scalariform (additional fork), wart-remover, native-packager, sbt-docker, ...
- editors
- ctags
- IntelliJ import
- console sbt settings, dependencies, ... - including plugins manipulation
- triggered tasks
- libs
- global settings
- useful/notable global plugins: sh, dependencyTree, ClearScreen, sbt-updates
- multi-project repo
- packaging (native and docker, ami?)
- editor navigation/command-pallet/implicits
- scalac flags: tpolecat's, ...
- logging implicits, i.e. -Xlog-implicits
- logging type info, i.e. -Xprint:typer
  - (I don't use it much, but could be good for folks to know about)
  - good example would be to show a case class of arity greater than 22 without the extension of Function.. and Tuple.. contrasted to a case class that does
- scalac -X, scalac -Y
- reify/showRaw
- learning resources
- thoughts now tending towards language features

## typeclasses
- might be a useful and slightly challenging topic to explain simply and succinctly

## 101 methods on List
- seems lengthy

## foldLeft vs foldRight
- what to show for this? seems trivial?
