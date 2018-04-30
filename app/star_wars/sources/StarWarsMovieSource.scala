package star_wars.sources

import star_wars.models.StarWarsMoviesByDirector

trait StarWarsMovieSource
{
    def getMoviesByDirector: Seq[StarWarsMoviesByDirector]


    def getCharactersForMovie(movie_id: String): Seq[StarWarsCharacter]
}
