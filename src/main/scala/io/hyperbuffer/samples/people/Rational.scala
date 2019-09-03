package io.hyperbuffer.samples.people

import scala.annotation.tailrec

case class Rational(numerator: Int, var denominator: Int) {
  require(denominator != 0)

  def *(other: Rational) = Rational(this.numerator * other.numerator, this.denominator * other.denominator)

  def /(other: Rational): Rational = {
    this * Rational(other.denominator, other.numerator)
  }

  def reduce(input: Rational): Rational = {
    @tailrec
    def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
    val divisor = gcd(input.numerator, input.denominator)
    Rational(numerator/divisor, denominator/divisor)
  }

  override def toString: String = {
    val reduced = reduce(Rational(numerator, denominator))

    reduced match {
      case Rational(0, _) => "0"
      case Rational(x, y) if x == y => "1"
      case _ => s"${reduced.numerator}/${reduced.denominator}"
    }
  }
}

object Rational {
//  def apply(numerator: Int, denominator: Int): Rational = new Rational(numerator, denominator)
//
//  def apply(numerator: Int): Rational = apply(numerator, 1)
//
//  def unapply(arg: Rational): Option[(Int, Int)] = Some(arg.numerator, arg.denominator)

//  implicit def int2Rational(x: Int) : Rational = Rational(x, 1)

}

class Pair[T] (first: T, second: T){

  def min[K <: Comparable[T]](a: K, t: T): Int ={
    a.compareTo(t)
  }
}