package by.vorobyov.stream

import net.liftweb.json.Serialization.write
import net.liftweb.json._

import scala.io.Source.fromFile
import scala.util.Random

object JsonGenerator {

  private val firstNameList = loadFirstName()
  private val lastNameList = loadLastName()

  def generateJson(): String = {
    val person: Person = generatePerson()
    implicit val formats = DefaultFormats
    write(person)
  }

  def generatePerson(): Person = {
    val randomAge = Random.nextInt(42) + 18
    val randomName = Random.nextInt(firstNameList.length)
    val randomLastName = Random.nextInt(lastNameList.length)
    new Person(firstNameList(randomName), lastNameList(randomLastName), randomAge.toString)
  }

  def loadFirstName(): List[String] = {
    val source = fromFile("/Users/philipvorobyov/devei/workspace/scala_spark/src/main/resources/firstName")
    try {
      source.getLines.flatMap(_.split("\\W+")).toList
    } finally {
      source.close()
    }
  }

  def loadLastName(): List[String] = {
    val source = fromFile("/Users/philipvorobyov/devei/workspace/scala_spark/src/main/resources/lastName")
    try {
      source.getLines.flatMap(_.split("\\W+")).toList
    } finally {
      source.close()
    }
  }
}
