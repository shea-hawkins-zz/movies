package star_wars.models

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
    title:      Seq[String],
    url:        String,
    vehicles:   Seq[String]
)
