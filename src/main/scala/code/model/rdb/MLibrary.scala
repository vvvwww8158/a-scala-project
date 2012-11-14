package code.model.rdb

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema

object MLibrary extends Schema {
   
  val logins = table[Login]("Login")
  on(logins)(entity => declare(
    entity.id is (dbType("int")),
    entity.password is (dbType("char(128)")),
    entity.about is (dbType("varchar(500"))
  ))
}