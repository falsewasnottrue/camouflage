package controllers

import com.google.inject.Inject
import play.api.mvc._
import services.SponService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class HomeController @Inject() (sponService: SponService) extends Controller {

  def index = Action.async {
    sponService.toc().map(articleInfos => Ok(views.html.index(articleInfos)))
    // sponService.toc().map(articleInfos => Ok(views.html.javadoc(articleInfos)))
  }

  def article(path: String) = Action.async {
    sponService.content(path).map(content => Ok(views.html.content(content)))
  }
}
