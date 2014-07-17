package controllers

import java.util.NoSuchElementException
import com.google.gson.Gson
import database.Dao
import database.dto.Event
import database.dto.LoginSession
import database.dto.User
import database.json.EventJson
import database.json.RegisterJson
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.Results

object Posts extends Controller {


	def login = Action { 
		request => 
		try {	
			val json = request.body.asJson.getOrElse(throw new NoSuchElementException("No JSON body supplied."));
			val gson = new Gson();
			val userJson: RegisterJson = gson.fromJson(json.toString, classOf[RegisterJson]);

			if(userJson.getEmail() == null || userJson.getEmail().isEmpty()) {
				throw new NoSuchElementException("No email address received.");
			}
			if(userJson.getPassword() == null || userJson.getPassword().isEmpty()) {
				throw new NoSuchElementException("No password received.");
			}

			if(!Dao.login(userJson.getEmail(), userJson.getPassword())) {
				throw new NoSuchElementException("Passwords do not match.")
			}
			val user = Dao.getUser(userJson.getEmail()).getOrElse(throw new NoSuchElementException("User " + userJson.getEmail() + " not found."));
			val loginSession = Dao.addSessionTokenToUser(user.getUserId()).getOrElse(throw new NoSuchElementException("")); //this needs to get fixed
			Ok(loginSession.toJson().toJsonString()).as("application/json");
		}
		catch {
		case e: NoSuchElementException => { BadRequest(e.getMessage()) };
		case e: Exception => { InternalServerError("Something went wrong...") }
		}

	}

	def register = Action {
		request => 
		try {
			val json = request.body.asJson.getOrElse(throw new NoSuchElementException("No JSON body supplied."));
			val gson = new Gson();
			val userJson: RegisterJson = gson.fromJson(json.toString, classOf[RegisterJson]);
			if(userJson.getEmail() == null || userJson.getEmail().isEmpty()) {
				throw new NoSuchElementException("No email address received.");
			}
			if(Dao.getUser(userJson.getEmail()).isDefined) {
				throw new NoSuchElementException("User already exists.");
			}
			if(userJson.getPassword() == null || userJson.getPassword().isEmpty()) {
				throw new NoSuchElementException("No password received.");
			}
			Dao.register(userJson.getEmail(), userJson.getPassword()).getOrElse(throw new Exception()); //need to manage this

			Created(""); //send token?
		}
		catch {
		case e: NoSuchElementException => { BadRequest(e.getMessage()) };
		case e: Exception => { InternalServerError("Something went wrong...") }
		}
	}

	def newEvent = Action {
		request => 
		try {
			val json = request.body.asJson.getOrElse(throw new NoSuchElementException("No JSON body supplied."));
			val gson = new Gson();
			val eventJson = gson.fromJson(json.toString, classOf[EventJson]);
			val event = Dao.addEvent(eventJson.getName(), eventJson.getStartTime(), eventJson.getEndTime())
					.getOrElse(throw new Exception("Something went wrong.."));
			Ok(event.toJson().toJsonString()).as("application/json");
		}
		catch {
		case e: NoSuchElementException => { BadRequest(e.getMessage()) };
		case e: Exception => { InternalServerError("Something went wrong...") }
		}
	}
	def attendEvent(eventId: Int) = Action {
		request => 
		try {
			val headersMap = request.headers.toMap;
			val token = headersMap.getOrElse("token", Seq(""))(0);
			val session = Dao.getLoginSession(token).getOrElse(throw new Exception("Forbidden. Must Auth.")); //must fix
			val user = session.getUser();
			val event = Dao.getEvent(eventId).getOrElse(throw new NoSuchElementException("No event found."));
			val attendingOpt = Dao.getEventAttendee(eventId, user.getUserId());
			if(attendingOpt.isDefined) {
				throw new IndexOutOfBoundsException("Already attending this event.");
			}
			val eventAttendance = Dao.attendEvent(event.getEventId(), user.getUserId());
			NoContent;
		}
		catch {
		case e: IndexOutOfBoundsException => { Conflict };
		case e: NoSuchElementException => { BadRequest(e.getMessage()) };
		case e: Exception => { InternalServerError("Something went wrong...") };
		}
	}

}