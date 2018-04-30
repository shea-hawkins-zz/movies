package star_wars.sources
import com.google.inject.{Inject, Singleton}
import exceptions.{InvalidResponseException, UndefinedConfigurationException}
import play.api.Configuration
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import star_wars.models.{StarWarsCharacter, StarWarsMovie, StarWarsMoviesByDirector}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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

        ws.url(api_url + route).get().map(
            resp => {
                // Parses the response to the expected output, otherwise throws an
                // exception
                Json.toJson(resp.body).asOpt[Seq[StarWarsMovie]].getOrElse(
                    throw new InvalidResponseException(
                        "The response from "
                            + api_url + route
                            + " could not be parsed. Body Received: "
                            + resp.body
                    )
                )
            }
        ).map(
            movies => {
                // Groups the movies by director, then returns the groupedobject
                movies.groupBy(_.director).map(
                    (director_movies) => {
                        val (director, movies) = director_movies

                        StarWarsMoviesByDirector(
                            director,
                            movies
                        )
                    }
                )
            }
        )
    }

    override def getCharactersForMovie(movie_id: String): Future[Seq[StarWarsCharacter]] = {

        Future { Nil }
    }


}
