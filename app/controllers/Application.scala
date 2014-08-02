package controllers

import database.HibernateService
import database.dto.User
import play.api._
import play.api.mvc._
import java.util.List
import database.Database
import database.Dao
import database.json.EventJson
import java.util.Date
import java.util.Calendar
import com.google.gson.Gson

object Application extends Controller {
	def index = Action {
	  Ok("Your new application is ready.");
	}
}