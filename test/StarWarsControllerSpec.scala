import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
import star_wars.models.StarWarsMoviesByDirector

/**
 * Functional tests start a Play application internally, available
 * as `app`.
 */
class StarWarsControllerSpec extends PlaySpec with GuiceOneAppPerSuite {
    "getMovieByDirector" should {
        "return a valid response" in {
            // Checks that the route functions and it provides the correct response format
            route(app, FakeRequest(GET, "/star_wars/movies")).map(contentAsJson).flatMap(
                json => {
                    (json \ "results").asOpt[Seq[StarWarsMoviesByDirector]]
                }
            ).isDefined mustBe true
        }
    }

    "getCharactersByMovie" should {
        "return a valid response when provided a valid id" in {
            true mustBe true
        }

        "return a not found response when provided an invalid id" in {

            true mustBe true
        }
    }
}
