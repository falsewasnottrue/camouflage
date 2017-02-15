package services

import javax.inject.Inject

import domain.ArticleInfo
import org.jsoup.Jsoup
import play.api.libs.ws.WSClient
import scala.collection.JavaConversions._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SponService @Inject() (ws: WSClient) {

  val prefix = "http://www.spiegel.de"

  def toc(): Future[Seq[ArticleInfo]] =
    ws.url(prefix).get().map { response =>
      val jsoup = Jsoup.parse(response.body)
      val teasers = jsoup.body().select("a[title]")
      teasers.toList.map(teaser => {
        val title = teaser.attr("title")
        val link = teaser.attr("href")
        ArticleInfo(title, link)
      }).distinct.filter(_.link.endsWith(".html"))
    }

  def content(path: String): Future[Seq[String]] =
    ws.url(prefix + "/" + path).get.map { response =>
      val jsoup = Jsoup.parse(response.body)
      val cs = jsoup.body().select("p")
      cs.toList.map(_.text())
    }
}