package code.model.rdb

import java.sql.DriverManager
import org.squeryl.SessionFactory
import org.squeryl.Session
import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.PrimitiveTypeMode._

object DbHelper {

  Class.forName("org.postgresql.Driver")

  def psql = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test", "postgres", "devpass")

  def registeDefaultSquerylSession() {
    SessionFactory.concreteFactory = Some(() => Session.create(psql, new PostgreSqlAdapter))
  }

  def squerylUsing[R](func: => R): R = {
    val session = Session.currentSessionOption getOrElse SessionFactory.newSession
    using(session)(func)
  }

}