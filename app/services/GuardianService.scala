package services
import javax.inject.Inject

import domain.Data
import play.api.libs.ws.WSClient

import scala.concurrent.Future

class GuardianService @Inject() (ws: WSClient) extends FriendlyContentProvider {

  override def toc(): Future[Seq[Data]] = ???

  override def content(path: String): Future[Seq[Data]] = ???
}
