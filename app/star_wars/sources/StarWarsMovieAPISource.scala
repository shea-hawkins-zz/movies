package star_wars.sources
import com.google.inject.{Inject, Singleton}
import exceptions.{NoResultsException, UndefinedConfigurationException}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import star_wars.models.{StarWarsCharacter, StarWarsMovie, StarWarsMoviesByDirector}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

@Singleton
class StarWarsMovieAPISource @Inject() (
    ws: WSClient,
    config: Configuration
) extends StarWarsMovieSource
{
    val api_url: String = config.getOptional[String]("star_wars.api.url").getOrElse(
        throw new UndefinedConfigurationException("The configuration variable for the star " +
            "wars api was not defined at star_wars.api.url")
    )



    override def getMoviesByDirector: Future[Seq[StarWarsMoviesByDirector]] = {
        val route: String = "/films/"
        ws.url(api_url + route).withRequestTimeout(10.seconds).get().map(
            resp => {
                // Parses the response to the expected output
                (Json.parse(resp.body) \ "results").as[Seq[StarWarsMovie]]

                    // Groups By Director
                    .groupBy(_.director).map(
                        (director_movies) => {
                            val (director, movies) = director_movies

                            StarWarsMoviesByDirector(director, movies)
                        }
                    ).toSeq
            }
        )
    }

    def getMovieById(movie_id: String): Future[Option[StarWarsMovie]] =
    {
        val route: String = "/films/" + movie_id
        ws.url(api_url + route).withRequestTimeout(10.seconds).get().map(
            resp => {
                // Parses the response to the expected output, returning the option of the
                // first entry
                Json.parse(resp.body).asOpt[StarWarsMovie]
            }
        )
    }

    override def getCharactersForMovie(movie_id: String): Future[Seq[StarWarsCharacter]] = {
        // Make the request for the movie
        getMovieById(movie_id).flatMap(
            movie => {
                // If we've gotten a movie,
                val requests: Seq[Future[StarWarsCharacter]] = movie.getOrElse(
                    throw new NoResultsException("No movies found for this movie id: " + movie_id)
                ).characters.map(
                    // we then request the characters for the movie
                    character_url => {
                        ws.url(character_url).get().map(
                            resp => {
                                Json.parse(resp.body).as[StarWarsCharacter]
                            }
                        )
                    }
                )

                // We then wait for all of the responses simultaneously
                Future.sequence(requests)
            }
        )
    }

}
