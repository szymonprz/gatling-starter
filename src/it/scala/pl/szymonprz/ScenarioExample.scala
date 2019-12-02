package pl.szymonprz

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import pl.szymonprz.ScenarioRequests._

import scala.concurrent.duration._

class ScenarioExample extends Simulation {

  private val httpProtocol = http
    .baseURLs(scenarioConfig.hosts.split(",").toList)
    .userAgentHeader("curl/gatling-starter")
    .disableCaching

  private val reverseAListScenario = scenario("Reverse a list").exec(ScenarioRequests.reverseAList)

  setUp(
    reverseAListScenario.inject(
      rampUsers(baseConfig.users) over (10 seconds)
    )
  ).maxDuration(10 seconds) protocols (httpProtocol)

}

object ScenarioRequests {

  val baseConfig: BaseConfig = ConfigLoader.baseConfig()
  val scenarioConfig: ScenarioConfig = ConfigLoader.scenarioConfig(baseConfig.env)
  private val languages = csv(s"language-${baseConfig.env}.csv").circular

  private val howToReverseAList: HttpRequestBuilder = http("Load info how to reverse a list")
    .get("${language}/reverse+a+list?T")

  val reverseAList = repeat(1) {
    feed(languages).exec(howToReverseAList)
  }

}
