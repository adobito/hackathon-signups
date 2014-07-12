package controllers

import play.api.mvc.Controller
import play.api.mvc.Action

object Puts extends Controller {

  def users(id: Int) = Action {
    Ok("TODO");
  }
}