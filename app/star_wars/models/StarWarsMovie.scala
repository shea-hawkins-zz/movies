package star_wars.models

import play.api.libs.json.Json

case class StarWarsMovie(
    characters: Seq[String],
    created:    String,
    director:   String,
    edited:     String,
    episode_id: Int,
    opening_crawl: String,
    planets:    Seq[String],
    producer:   String,
    release_date: String,
    species:    Seq[String],
    starships:  Seq[String],
    title:      String,
    url:        String,
    vehicles:   Seq[String]
)

object StarWarsMovie
{
    implicit val format = Json.format[StarWarsMovie]
}
