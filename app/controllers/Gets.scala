package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.google.gson.Gson
import database.Dao
import database.json.SkillJson
import database.json.UniversityJson
import database.json.EventJson
import database.dto.User

object Gets extends Controller {
	private val gson = new Gson();

	def usersAll = Action {
		val users = Dao.getUsers(0, 20).toArray;
		Ok(gson.toJson(users));
	}
	def users(id: Int) = Action {
		request => 
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val sessionOpt = Dao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found.");
			}
			val userOpt = Dao.getUser(id);
			val user = userOpt.getOrElse(throw new NoSuchElementException("No user with user_id = " + id));

			Ok(user.toJson().toJsonString()).as("application/json");

		}
		catch {
		case e: NoSuchElementException => NotFound(e.getMessage());
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}
	def userSkills(userId: Int) = Action {
		val skills = Dao.getSkillsByUser(userId);
		val jsonArr = new Array[SkillJson](skills.size);
		for(i <- 0 until skills.size) {
			jsonArr(i) = new SkillJson(skills(i));
		}
		Ok(gson.toJson(jsonArr)).as("application/json");
	}
	def userMe = Action {
		request => 
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val user = Dao.getLoginSession(token).getOrElse(throw new Exception("BOOM")).getUser();
			Ok(user.toJson().toJsonString());
		}
		catch {
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}
	def logout = Action {
		request => 
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val loginSessionOpt = Dao.getLoginSession(token);
			if(loginSessionOpt.isDefined) {
				Dao.deleteSessionToken(loginSessionOpt.get.getToken());
			}
			Ok("Logged out successfully.");
		}
		catch {
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}
	def universities = Action {
		request => 
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val sessionOpt = Dao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found.");
			}
			val universities = Dao.getUniversities();
			val univertiesArr = new Array[UniversityJson](universities.size);
			for(i <- 0 until univertiesArr.size) {
				univertiesArr(i) = universities(i).toJson().asInstanceOf[UniversityJson];
			}
			Ok(gson.toJson(univertiesArr)).as("application/json");

		}
		catch {
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}
	def event(id: Int) = Action {
		request => 
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val sessionOpt = Dao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found.");
			}
			val event = Dao.getEvent(id).getOrElse(throw new NoSuchElementException("Event with event id = " + id + " not found."));
			Ok(event.toJson().toJsonString()).as("application/json");

		}
		catch {
		case e: NoSuchElementException => NotFound(e.getMessage());
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}
	def eventAttendees(id:Int) = Action {
		NotFound("Nothing here yet.");
	}

}