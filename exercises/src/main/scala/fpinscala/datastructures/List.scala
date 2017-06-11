package fpinscala.datastructures

/**
  * _dv: is for type variance. Means if have B as a subtype of A, then B can be in this list too.
  */
sealed trait List[+A] // `List` data type, parameterized on a type, `A`

/**
  * _dv: Nothing is a base class of all objects. So with the polymorphic parameter A haing the + for variance,
  * then Nil can represent an empty list of any actual type.
  */
/**
  * _dv: below gives 2 constructors for the data type:
  */
case object Nil extends List[Nothing] // A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]


/**
  * _dv: This is often called the COMPANION OBJECT to List
  */
object List { // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x,xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
    /**
      * _dv: Noting about Cons() constructor calls these parameters head,tail which is clearer
      */
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  /**
    * _dv: it is this method that allows construction of data types via e.g. List(1,2,3)
    * -- the A* means that we can input as many A objects as we wish
    */
  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1,2,3,4,5) match {
      // no: goes 2,3,4 not 2,4,blah
    case Cons(x, Cons(2, Cons(4, _))) => x
      // no: not empty
    case Nil => 42
      // yes: wildcards on 1,2, then matches 3,4, * --> x+y = 3
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      // yes: but will hit first match above. this would be head + sum(tail) = sum(x)
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x,y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar


  def tail[A](l: List[A]): List[A] = ???

  def setHead[A](l: List[A], h: A): List[A] = ???

  def drop[A](l: List[A], n: Int): List[A] = ???

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = ???

  def init[A](l: List[A]): List[A] = ???

  def length[A](l: List[A]): Int = ???

  def foldLeft[A,B](l: List[A], z: B)(f: (B, A) => B): B = ???

  def map[A,B](l: List[A])(f: A => B): List[B] = ???
}

object Play {

  val x = List(1,2,3,4)

  def p[A](a:A) : Unit = {
    println(a)
  }

  def matchPlay() : Unit = {
    p(x match {case Cons(h,_) => "123"})
    p(x match {case Cons(_,_) => "123"})
    p(x match {case Cons(_,t) => t})
  }

  def listUtiltyObject(): Unit = {
    def aid[A](as:List[A], op:String, f: (List[A]) => Int) : String = {
      op + " of list: " + as + " is " + f(as).toString
    }
    val y = List(3,4,5)
    p(aid(y, "sum", List.sum))
    p(List.sum(List(1,2,3,4)))
  }

  def main(args: Array[String]) : Unit = {
    p(x)
    p("I predict the matches defined on x will return sum of x,y ie 3:")
    p(List.x)
    matchPlay()
    p("check unitilty methods in List object")
    listUtiltyObject()

    Unit
  }
}
