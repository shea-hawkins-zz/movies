name := """star-wars"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")
scalaVersion := "2.12.5"

//Add library dependency for the project
libraryDependencies ++= Seq(
    ehcache,
    guice,
    jdbc,
    ws,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
)
