package star_wars.sources
import com.google.inject.{Inject, Singleton}
import play.api.libs.ws.WSClient
import star_wars.models.{StarWarsCharacter, StarWarsMoviesByDirector}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class StarWarsMovieAPISource @Inject() (
    ws: WSClient
) extends StarWarsMovieSource
{
    override def getCharactersForMovie(movie_id: String): Future[Seq[StarWarsCharacter]] = {

        Future { Nil }
    }

    override def getMoviesByDirector: Future[Seq[StarWarsMoviesByDirector]] = {

        Future { Nil }
    }
}
