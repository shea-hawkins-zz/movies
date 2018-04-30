import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
import star_wars.models.{StarWarsCharacter, StarWarsMoviesByDirector}

/**
 * Functional tests start a Play application internally, available
 * as `app`.
 */
class StarWarsControllerSpec extends PlaySpec with GuiceOneAppPerSuite {
    "getMovieByDirector" should {
        "return a valid response" in {
            // Checks that the route functions and it provides the correct response format
            val response = route(app, FakeRequest(GET, "/star_wars/movies")).map(contentAsJson).flatMap(
                json => {
                    (json \ "results").asOpt[Seq[StarWarsMoviesByDirector]]
                }
            )

            response.isDefined mustBe true
            response.getOrElse(Nil).nonEmpty mustBe true
        }
    }

    "getCharactersByMovie" should {
        "return a valid response when provided a valid id" in {
            val response = route(app, FakeRequest(GET, "/star_wars/movies/1/characters")).map(contentAsJson).flatMap(
                json => {
                    (json \ "results").asOpt[Seq[StarWarsCharacter]]
                }
            )

            response.isDefined mustBe true
            response.getOrElse(Nil).nonEmpty mustBe true
        }

        "return a not found response when provided an invalid id" in {
                                                           // see you in 2200
            val response = route(app, FakeRequest(GET, "/star_wars/movies/42/characters"))

            response.map(status).getOrElse(0) mustBe 404
            response.map(contentAsJson).flatMap(
                json => {
                    (json \ "results").asOpt[Seq[StarWarsCharacter]]
                }
            ).get.isEmpty mustBe true
        }
    }
}
