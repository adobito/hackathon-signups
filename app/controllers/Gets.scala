package controllers

import java.util.Date

import com.google.gson.Gson
import database.dao._
import database.dto.{Sex, ShirtSize}
import database.json.{SexJson, ShirtSizeJson, UniversityJson}
import play.api.mvc.{Action, Controller}
import utils.JsonUtils

object Gets extends Controller {
	private val gson = new Gson();

	def usersAll = Action {
		val users = UserDao.getUsers(0, 20).toArray;
		Ok(gson.toJson(users));
	}
	def users(id: Int) = Action {
		request =>
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val sessionOpt = UserDao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found.");
			}
			val userOpt = UserDao.getUser(id);
			val user = userOpt.getOrElse(throw new NoSuchElementException("No user with user_id = " + id));

			Ok(user.toJson().toJsonString()).as("application/json");

		}
		catch {
		case e: NoSuchElementException => NotFound(e.getMessage());
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}
		def events = Action {
		request => 
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val sessionOpt = UserDao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found.");
			}
			val now = new Date();
			val eventsList = EventDao.getEventsEndingAfter(now,0,20);
			val array = JsonUtils.jsonableListToJsonArray(eventsList);
			Ok(JsonUtils.toJsonString(array)).as("application/json");

		}
		catch {
		case e: NoSuchElementException => NotFound(e.getMessage());
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}
//	def userSkills(userId: Int) = Action {
//		val skills = UserDao.getSkillsByUser(userId);
//		val jsonArr = new Array[SkillJson](skills.size);
//		for(i <- 0 until skills.size) {
//			jsonArr(i) = new SkillJson(skills(i));
//		}
//		Ok(gson.toJson(jsonArr)).as("application/json");
//	}
	def userMe = Action {
		request =>
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val user = UserDao.getLoginSession(token).getOrElse(throw new Exception("BOOM")).getUser();
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
			val loginSessionOpt = UserDao.getLoginSession(token);
			if(loginSessionOpt.isDefined) {
				UserDao.deleteSessionToken(loginSessionOpt.get.getToken());
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
			val sessionOpt = UserDao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found.");
			}
			val universities = UniversityDao.getUniversities();
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
			val sessionOpt = UserDao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found.");
			}
			val event = EventDao.getEvent(id).getOrElse(throw new NoSuchElementException("Event with event id = " + id + " not found."));
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
//	def permissionsGroups(userId: Int) = Action {
//		request =>
//		try {
//			val headersMap = request.headers.toMap;
//			val token = headersMap.getOrElse("token", Seq(""))(0);
//			val sessionOpt = UserDao.getLoginSession(token);
//			if(sessionOpt.isEmpty) {
//				throw new Exception("No active session found.");
//			}
//			val user = UserDao.getUser(userId).getOrElse(throw new NoSuchElementException("No used with user id = " + userId));
//			val permissions = UserDao.getPermissionsGroups(userId);
//			Ok(gson.toJson(permissions.toArray)).as("application/json");
//
//		}
//		catch {
//		case e: NoSuchElementException => NotFound(e.getMessage());
//		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
//		}
//	}
	def sexes = Action {
		request =>
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val sessionOpt = UserDao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found."); // should say authenticate
			}
			val sexes: List[Sex] = SexDao.getSexes();
			val sexesArr = new Array[SexJson](sexes.size);
			for(i <- 0 until sexesArr.size) {
				sexesArr(i) = sexes(i).toJson().asInstanceOf[SexJson];
			}
			Ok(gson.toJson(sexesArr)).as("application/json");

		}
		catch {
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}
	def shirts = Action {
	  		request =>
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val sessionOpt = UserDao.getLoginSession(token);
			if(sessionOpt.isEmpty) {
				throw new Exception("No active session found."); // should say authenticate
			}
			val shirts: List[ShirtSize] = ShirtSizeDao.getShirtSizes();
			val shirtsArr = new Array[ShirtSizeJson](shirts.size);
			for(i <- 0 until shirtsArr.size) {
				shirtsArr(i) = shirts(i).toJson().asInstanceOf[ShirtSizeJson];
			}
			Ok(gson.toJson(shirtsArr)).as("application/json");

		}
		catch {
		case e: Exception => { e.printStackTrace(); InternalServerError("Something went wrong...") }
		}
	}


}
