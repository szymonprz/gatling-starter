package pl.szymonprz

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory._
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

object ConfigLoader {

  private def loadConfigurationFrom(configuration: String): Config =
    systemProperties().withFallback(systemEnvironment().withFallback(parseResourcesAnySyntax(configuration))).resolve()

  def baseConfig(): BaseConfig = loadConfigurationFrom("application").as[BaseConfig]("application")

  def scenarioConfig(env: String): ScenarioConfig = {
    loadConfigurationFrom(s"application-$env").as[ScenarioConfig]("configuration")
  }
}

case class BaseConfig(env: String, users: Int)

case class ScenarioConfig(hosts: String)