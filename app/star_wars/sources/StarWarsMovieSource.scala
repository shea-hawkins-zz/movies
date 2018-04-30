package star_wars.sources

import star_wars.models.{StarWarsCharacter, StarWarsMoviesByDirector}

import scala.concurrent.Future

/**
 * An abstract interface for a star wars movie source
 * (Used for easily swapping out the datasources: For example, if the mission were life-and-death
 * and our API response determined whether or not the Millennium Falcon escaped near destruction,
 * we'd want to start storing the movies locally and swap out the module)
 */
trait StarWarsMovieSource
{
    /**
     * Gets the list of movies grouped by director
     * @return Future Sequence of Movies
     */
    def getMoviesByDirector: Future[Seq[StarWarsMoviesByDirector]]

    /**
     * Gets the list of characters given a movie_id
     * @param movie_id
     * @return Future Sequence of Characters
     */
    def getCharactersForMovie(movie_id: String): Future[Seq[StarWarsCharacter]]
}
