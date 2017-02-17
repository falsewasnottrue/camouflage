package controllers

import com.google.inject.Inject
import play.api.mvc._
import services.SponService

import scala.concurrent.ExecutionContext.Implicits.global

class ContentController @Inject()(sponService: SponService) extends Controller {

  def spon = Action.async {
    sponService.toc().map(ds => Ok(views.html.javadoc(ds, "spon")))
  }

  def sponArticle(path: String) = Action.async {
    sponService.content(path).map(ds => Ok(views.html.javadoc(ds, "spon")))
  }
}
