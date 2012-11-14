package code.model.rdb

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.annotations._
import org.squeryl.{ Query, KeyedEntity }

class Login(
  @Column("id") var id: Int,
  @Column("username") var username: String,
  @Column("passwd") var password: String,
  @Column("about") var about: String = "") extends KeyedEntity[Int] {

}