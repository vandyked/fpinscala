/**
sealed: all objects of this type must be defined in this file 
trait: a data class in scala-- called a "data type"
sealed trait List[+A]:  + polymorphic parameter variance (allows subtypes of A)

*/

object DV{
sealed trait List[+A]
case object Nil extends List[Nothing]   // have inheritence here. 
case class Cons[+A](head: A, tail: List[A]) extends List[A]

var x = Cons(3, Cons(2, Nil))

def main(args: Array[String]) : Unit = {
//    List(1,2,3) match {case Cons(h,_) => h}
//    List(1,2,3) match {case _ => "this is always the answer"}  // warning as pure express does nothing in statement position
    List(3,4,5) match {case _ => 42}  // same warning
}

}
