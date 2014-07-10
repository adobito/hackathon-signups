package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import com.google.gson.Gson
import database.Dao
import database.dto.UserInfo
import database.json.UserInfoJsonDto

object Gets extends Controller {

  	def usersAll = Action {
	  val gson = new Gson();
	  val users = Dao.getUserInfo(0, 20);
	  Ok(gson.toJson(users));
	}
	def users(id: Int) = Action {
	  val gson = new Gson();
	  val userInfoOpt = Dao.getUserInfo(id);
	  val userInfo = userInfoOpt.getOrElse(new UserInfo());
	  val userInfoJson = new UserInfoJsonDto(userInfo);
	  
	  Ok(gson.fromJson(userInfoJson.toString(),classOf[UserInfoJsonDto]).toString());
	}
	def userMe = Action {
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
	
}