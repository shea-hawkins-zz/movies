package star_wars.models

import play.api.libs.json.Json

case class StarWarsMoviesByDirector(
    director: String,
    movies: Seq[StarWarsMovie]
)

object StarWarsMoviesByDirector
{
    implicit val format = Json.format[StarWarsMoviesByDirector]
}


