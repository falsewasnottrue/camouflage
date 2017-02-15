package domain

case class Data(title: String, link: String) {
  def l = s""""$link""""
  def t = s""""${title.replaceAll("\"", "")}""""
}
