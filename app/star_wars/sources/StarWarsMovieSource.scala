package star_wars.sources

import star_wars.models.{StarWarsCharacter, StarWarsMoviesByDirector}

import scala.concurrent.Future

trait StarWarsMovieSource
{
    def getMoviesByDirector: Future[Seq[StarWarsMoviesByDirector]]


    def getCharactersForMovie(movie_id: String): Future[Seq[StarWarsCharacter]]
}
