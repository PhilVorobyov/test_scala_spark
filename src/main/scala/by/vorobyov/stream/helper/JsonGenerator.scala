package by.vorobyov.stream.helper

import by.vorobyov.stream.helper.PropertiesHelper.prop
import by.vorobyov.stream.model.Person
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

import scala.io.Source.fromFile
import scala.util.Random

/** Generate random json person in json format. */
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
    val source = fromFile(prop.getProperty("first_name_path"))
    try {
      source.getLines.flatMap(_.split("\\W+")).toList
    } finally {
      source.close()
    }
  }

  def loadLastName(): List[String] = {
    val source = fromFile(prop.getProperty("last_name_path"))
    try {
      source.getLines.flatMap(_.split("\\W+")).toList
    } finally {
      source.close()
    }
  }
}
