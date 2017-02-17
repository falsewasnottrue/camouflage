package services

import domain.Data

import scala.concurrent.Future

trait FriendlyContentProvider {

  def toc(): Future[Seq[Data]]

  def content(path: String): Future[Seq[Data]]

}
