package io.hyperbuffer.samples.people

import java.time.OffsetDateTime
import java.util.UUID
import java.util.concurrent.{Executors, TimeUnit}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

object Gender extends Enumeration {
  val Male: Gender.Value = Value(1)
  val Female: Gender.Value = Value(2)
  val Other: Gender.Value = Value(0)
}


trait Logger {
  def log(msg: String)
}

trait ConsoleLogger extends AnyRef with Logger {
  def log(msg: String): Unit = println(msg)
}

trait ShortLogger extends Logger {
  val maxLength = 10

  abstract override def log(msg: String): Unit = {
    super.log(
      if (msg.length <= maxLength) msg
      else s"${msg.substring(0, 10)}"
    )
  }
}

trait TimeLogger extends Logger {
  abstract override def log(msg: String): Unit = super.log(s"${OffsetDateTime.now()} $msg")
}

abstract case class Person(firstName: String, lastName: String, var age: Int = 0) extends ConsoleLogger {

  var id: String
  var gender: Gender.Value = Gender.Other

  def this(firstName: String, lastName: String, gender: Gender.Value, age: Int) {
    this(firstName, lastName)
    this.age = age
    this.gender = gender
  }

}

class Employee(firstName: String, lastName: String) extends Person(firstName = firstName, lastName = lastName) with ConsoleLogger with TimeLogger with ShortLogger {
  override var id: String = "Emp:" + UUID.randomUUID().toString
  this.log("hello " + firstName)
}

//case class Employee extends Person(){}
object Greeting extends App {

  val ctx: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor())
  val f = (1 to 10).map(x => Future({
    Thread.sleep(3000);
    x * 2
  })(ctx))

  val result = 2 * Rational(3, 6)
  println(result)
  println(5.denominator)

}