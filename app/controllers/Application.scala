package controllers

import database.HibernateService
import database.dto.User
import play.api._
import play.api.mvc._
import java.util.List
import database.Database
import database.Dao

object Application extends Controller {

	def index = Action {
		Ok(views.html.index("Your new application is ready."));
	}
	

}