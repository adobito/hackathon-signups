package controllers

import java.util.Date

import database.dao.EventDao
import play.api.mvc._

object EventsController extends Controller {

  def getEvents = Action {
    Ok(EventDao.getEventsStartingBefore(new Date()).mkString("\n"));
  }
}