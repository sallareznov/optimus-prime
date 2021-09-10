import java.io.File

import sbt.Def.SettingsDefinition
import sbt.Keys._
import sbt._
import complete.DefaultParsers._

import scala.util.{Failure, Success, Try}

object CucumberSettings {

  val cucumber = inputKey[Unit]("Run Cucumber tests")

  def cucumberSettings(path: String): SettingsDefinition = {
    SettingsDefinition.wrapSettingsDefinition {

      def runCucumber(classPath: List[String], cucumberArgs: Seq[String] = Nil): Unit = {
        Try {
          val classPathArgs = Vector("-cp", classPath.mkString(File.pathSeparator))
          Fork.java(
            ForkOptions().withRunJVMOptions(classPathArgs),
            Seq(path, "local") ++ cucumberArgs
          )
        } match {
          case Success(0)     => ()
          case Success(code)  => throw new RuntimeException(s"cucumber terminated with code $code")
          case Failure(error) => throw error
        }
      }

      Seq(
        cucumber := {
          val classPath: List[String] = (Test / fullClasspath).map(_.toList.map(_.data.toString)).value
          val cucumberArgs            = spaceDelimited("<arg>").parsed
          runCucumber(classPath, cucumberArgs)
        }
      )
    }
  }
}
