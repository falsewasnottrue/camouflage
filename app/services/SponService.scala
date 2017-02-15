package services

import javax.inject.Inject

import domain.ArticleInfo
import org.jsoup.Jsoup
import play.api.libs.ws.WSClient
import scala.collection.JavaConversions._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class SponService @Inject() (ws: WSClient) {

  def toc(): Future[Seq[ArticleInfo]] =
    ws.url("http://www.spiegel.de").get().map { response =>
      val jsoup = Jsoup.parse(response.body)
      val teasers = jsoup.body().select("a[title]")
      teasers.toList.map(teaser => {
        val title = teaser.attr("title")
        val link = teaser.attr("href")

        val summary = "TODO"
        println(s"TITLE $title")
        ArticleInfo(title, summary, link)
      })
    }
}