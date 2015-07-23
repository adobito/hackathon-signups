package controllers
import java.util.Date

import database.dao.{EventDao, UserDao}
import database.dto.Event
import play.api.mvc._

object Application extends Controller {
	def index = Action {
	  Ok(views.html.bootstrap())
	}
}