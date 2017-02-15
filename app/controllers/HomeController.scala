package controllers

import com.google.inject.Inject
import play.api.mvc._
import services.SponService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HomeController @Inject() (sponService: SponService) extends Controller {

  def index = Action.async {
    sponService.toc().map(ds => Ok(views.html.javadoc(ds)))
  }

  def article(path: String) = Action.async {
    sponService.content(path).map(ds => Ok(views.html.javadoc(ds)))
  }
}
