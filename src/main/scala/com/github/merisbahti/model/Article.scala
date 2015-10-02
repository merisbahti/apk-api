import slick.driver.SQLiteDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.xml.XML

class Articles(tag: Tag) extends Table[(String, Double, Double, Double, String, String, String, Double)](tag, "ARTICLES") {
  def name               = column[String]("NAME")
  def price              = column[Double]("PRICE") // kr
  def volume             = column[Double]("VOLUME") // ml
  def alcohol_percentage = column[Double]("ALCOHOL_PERCENTAGE")
  def type_name          = column[String]("TYPE_NAME")
  def sub_type_name      = column[String]("SUB_TYPE_NAME")
  def varnummer          = column[String]("VARNUMMER")
  def apk                = column[Double]("APK")
  // Every table needs a * projection with the same type as the table's type parameter
  def * = (name, price, volume, alcohol_percentage, type_name, sub_type_name, varnummer, apk)
}

object TestDatabase extends App {
  val db = Database.forURL( "jdbc:sqlite:test.sqlite", driver = "org.sqlite.JDBC" )
  val articles = TableQuery[Articles]
  try {
    val setup = DBIO.seq(
      articles.schema.create,
      articles ++= Seq(("Meris Öl", 10.0, 75.0, 12.5, "Öl", "Mörk lager", "1337", 93.75))
    )
    val setupFuture = db.run(setup);
  } finally db.close()
  val xml = XML.loadFile("./xml")
  val article_seqs = for { x <- xml \\ "artikel" }
  yield ((x \\ "Namn" text),
         (x \\ "Prisinklmoms" text),
         (x \\ "Volymiml" text),
         (x \\ "Alkoholhalt" text),
         (x \\ "Varugrupp" text),
         (x \\ "Varugrupp" text),
         (x \\ "Varnummer" text),
         (x \\ "apk" text))
  article_seqs.foreach(println(_))
}
