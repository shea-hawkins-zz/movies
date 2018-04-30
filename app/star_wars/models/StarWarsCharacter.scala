package star_wars.models

import play.api.libs.json.Json

case class StarWarsCharacter(
    name:       String,
    mass:       String,
    height:     String,
    hair_color: String,
    skin_color: String,
    eye_color:  String,
    birth_year: String,
    gender:     String,
    homeworld: String,
    films:      Seq[String],
    species:    Seq[String],
    vehicles:   Seq[String],
    starships:  Seq[String],
    created:    String,
    edited:     String,
    url:        String
)

object StarWarsCharacter {
    implicit val format = Json.format[StarWarsCharacter]
}
