package by.vorobyov.stream.helper

import java.io.FileInputStream
import java.util.Properties

/** Properties helper. */
object PropertiesHelper {
  val prop: Properties = new Properties()
  prop.load(new FileInputStream("src/main/resources/application.properties"))
}
