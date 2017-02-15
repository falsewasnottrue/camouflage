package services

import javax.inject.Inject

import domain.Data
import org.jsoup.Jsoup
import play.api.libs.ws.WSClient
import scala.collection.JavaConversions._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SponService @Inject() (ws: WSClient) {

  val prefix = "http://www.spiegel.de"

  def toc(): Future[Seq[Data]] =
    ws.url(prefix).get().map { response =>
      val jsoup = Jsoup.parse(response.body)
      val teasers = jsoup.body().select("a[title]")
      teasers.toList.map(teaser => {
        val title = sanitize(teaser.attr("title"))
        val link = teaser.attr("href")
        Data(title, link)
      }).filter(_.link.endsWith(".html")).distinct
    }

  def content(path: String): Future[Seq[Data]] =
    ws.url(prefix + "/" + path).get.map { response =>
      val jsoup = Jsoup.parse(response.body)
      val cs = jsoup.body().select("p")
      cs.toList.map(elem => Data(sanitize(elem.text()), ""))
    }

  private def sanitize(s: String) =
    s.toLowerCase().replaceAll("\"", "").replaceAll("spiegel", "igel").replaceAll("online", "allein")
}