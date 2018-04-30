package star_wars

import exceptions.InvalidResponseException
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import star_wars.sources.StarWarsMovieSource

import scala.concurrent.ExecutionContext

@Singleton
class StarWarsController @Inject()(
   cc: ControllerComponents,
   movie_source: StarWarsMovieSource
)(implicit exec: ExecutionContext) extends AbstractController(cc) {

    def getMoviesByDirector = Action.async {

        movie_source.getMoviesByDirector.map(
            movies => {
                Ok(
                    Json.obj(
                        "results" -> Json.toJson(movies)
                    )
                )
            }
        ).recover {
            case e: InvalidResponseException =>
            {

                InternalServerError(
                    Json.obj(
                        "error" -> ("Received an invalid response from the force: " + e.getMessage)
                    )
                )
            }
            case e: Exception =>
            {
                InternalServerError(
                    Json.obj(
                        "error" -> ("An error was encountered while retrieving the movies. Message: " + e.getMessage)
                    )
                )
            }
        }
    }

}
