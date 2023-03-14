import sbt.Keys.{fork, _}
import sbt._

object BuildConfig {
  def isLegacy(v: String): Boolean = {
    v.startsWith("2.10.")
  }

  object Revision {
    lazy val revision = System.getProperty("revision", "SNAPSHOT")
  }

  def commonSettings(currentVersion: String) = {
    Seq(
      organization := "com.curalate",

      version := s"$currentVersion-${BuildConfig.Revision.revision}",

      credentials += Credentials(Path.userHome / ".sbt" / "credentials"),

      scalaVersion := "2.12.10",

      publishMavenStyle := true,

      // only append _major.minor for non 2.10 cross builds
      crossPaths := !BuildConfig.isLegacy(scalaVersion.value),

      fork in Test := true,

      publishTo in Global := {
        val nexus = "https://maven.curalate.com/"
        if (isSnapshot.value)
          Some("snapshots" at nexus + "content/repositories/snapshots")
        else
          Some("releases" at nexus + "content/repositories/releases")
      }
    )
  }
}
