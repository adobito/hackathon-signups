package utils

import database.dto.Jsonable
import database.json.Json
import com.google.gson.Gson

object JsonUtils {
  private val gson = new Gson();
  def jsonableListToJsonArray(jsonables: List[Jsonable]): Array[Json] = {
    val jsonArray = new Array[Json](jsonables.size);
    for(i <- 0 until jsonables.size) {
      jsonArray(i) = jsonables(i).toJson();
    }
    jsonArray;
  }
  def toJsonString(obj: Any): String = {
    gson.toJson(obj);
  }
  def getGson: Gson = {
    gson;
  }
}