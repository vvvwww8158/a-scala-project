package code.service

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Query

import code.model.rdb.DbHelper
import code.model.rdb.Login
import code.model.rdb.MLibrary

object LoginService {

  DbHelper.registeDefaultSquerylSession()
  def addUser(users: Login*): Either[Exception, Int] =
    try {
      transaction(MLibrary.logins insert users)
      Right(users.size)
    } catch {
      case e: Exception =>
        Left(e)
    }
  def insertOrUpdate(user: Login): Either[Exception, Login] =
    try {
      val ret = transaction(MLibrary.logins.insertOrUpdate(user))
      Right(ret)
    } catch {
      case e: Exception =>
        Left(e)
    }

  def findUser(username: String): Query[Login] = {
    MLibrary.logins.where(l => l.username === username)
  }

  def checkLogin(username: String, passwd: String): Option[Login] = {
    try {
      val u = DbHelper.squerylUsing(findUser(username).single)
      if (u.password == passwd)
        Some(u)
      else
        None
    } catch {
      case e: Exception =>
        println(e.getMessage())
        None
    }

  }
  def main(args: Array[String]) {
    /*    val user = new Login(2, "postgre", "123456", "abcd")
    println(user.id)
    val a = insertOrUpdate(user)
    println(a)*/

/*    val ret = checkLogin("postgre", "123456")

    ret match {
      case Some(u) =>
        println("login success")
      case None =>
        println("no")
    }*/

  }

}