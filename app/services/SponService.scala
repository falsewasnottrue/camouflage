package services

import javax.inject.Inject

import domain.ArticleInfo
import play.api.libs.ws.WSClient

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SponService @Inject() (ws: WSClient) {

  def toc(): Future[Seq[ArticleInfo]] = {
    ws.url("http://www.spiegel.de").get().map { response =>
      println(response.body)
      Nil
    }
  }
}
