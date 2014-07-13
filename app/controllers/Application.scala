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
	  val skills = Dao.getSkillsByUser(1);
	  println(skills.size);
	  skills.foreach(x => println(x.getName()));

	  val calendar = Calendar.getInstance();
	  calendar.set(2014, Calendar.SEPTEMBER, 27, 12, 0, 0);
	  val startDate = calendar.getTime();
	  calendar.set(2014, Calendar.SEPTEMBER, 28, 18, 0, 0);
	  val endDate = calendar.getTime();
	  	  val eventJson = new EventJson();
	  eventJson.setName("HackPR Fall 2014");
	  eventJson.setStartTime(startDate);
	  eventJson.setEndTime(endDate);
	  println(new Gson().toJson(eventJson));
	  Ok(views.html.index("Your new application is ready."));
	}
}