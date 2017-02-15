package controllers

import com.google.inject.Inject
import play.api.mvc._
import services.SponService

import scala.concurrent.ExecutionContext.Implicits.global

class HomeController @Inject() (sponService: SponService) extends Controller {

  def index = Action.async {
    sponService.toc().map(articleInfos => Ok(views.html.index(articleInfos)))
  }

}
