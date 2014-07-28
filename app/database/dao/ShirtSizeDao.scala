package database.dao

import database.dto.ShirtSize

object ShirtSizeDao {

  def getShirtSize(id: Int): Option[ShirtSize] = {
    None;
  }
  def getShirtSize(name: String): Option[ShirtSize] = {
    None;
  }
  def getShirtSizes(): List[ShirtSize] = {
    Nil;
  }
}