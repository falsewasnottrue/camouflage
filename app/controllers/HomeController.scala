package controllers

import play.api.mvc._
import services.SponService

class HomeController extends Controller {

  private val sponService = new SponService

  def index = Action {
    val articleInfos = sponService.toc()

    Ok(views.html.index(articleInfos))
  }

}
