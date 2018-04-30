package star_wars

import exceptions.NoResultsException
import javax.inject.{Inject, Singleton}
import play.api.libs.json.{Json, Writes}
import play.api.mvc.{AbstractController, ControllerComponents, Result}
import star_wars.sources.StarWarsMovieSource

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class StarWarsController @Inject()(
   cc: ControllerComponents,
   movie_source: StarWarsMovieSource
)(implicit exec: ExecutionContext) extends AbstractController(cc) {

    def getMoviesByDirector = Action.async {
        handleResponse(movie_source.getMoviesByDirector)
    }


    def getCharactersForMovie(movie_id: String) = Action.async {
        handleResponse(movie_source.getCharactersForMovie(movie_id))
    }

    private def handleResponse[ResponseFormat: Writes](result: Future[ResponseFormat]): Future[Result]= {
        result.map(
            movies => {
                Ok(
                    Json.obj(
                        "results" -> Json.toJson(movies)
                    )
                )
            }
        ).recover {
            case e: NoResultsException =>
            {

                NotFound(
                    Json.obj(
                        "results" -> Nil
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
