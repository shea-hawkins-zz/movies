package utils

import play.api.libs.json.{Json, Reads}

object JsonUtils
{
    def toModel[Model: Reads](string: String): Option[Model] = {
        Json.toJson(string).asOpt[Model]
    }
}
