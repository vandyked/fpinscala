/**

***Notes on Scala syntax***
- 
- Object: Creates a singleton instance of the code within it. Like inner class in java.
- Return types are generally implied, but better to state them
- main is the access point. It is a non-pure function ==> call **Procedure** or **Impure function**
- Scala uses **Unit** as the equivalent of void. () is also Unit. Generally Unit hints that the method is not pure. 

*/


// REPL is great for learning scala - cmd line interpreter. 

object Play {

var x:Int = 5; // semicolon not mandatory

// Pretty cool! + can be a method name in scala. 
def +(y:Int) : Int = {
    x+y
}

def printGiven(x: Int)  = {
    println(x)
}

def main(args: Array[String]) : Unit = {
    println(33)
}
}

object ScalaSyntax {
  import Play._; // import all (non-private) methods from Play
  def UseImportFromPlay(y:Int) : Unit = {
      println(+(y));  // will use + from ?
    println(Play.+(y)); // will use + from Play
  }
}


object TailPosition {
    /**
        -Scala relies on compiling recursive functions that are in TAIL POSITION - into iterative loops --AVOIDING STACK OVERFLOW 
        -Scala doesn't warn by default if it couldn't compile into iterative function -- can use annotations to get this:
    */

    def TailExample(i: Int, n:Int) : Int = {
        @annotation.tailrec   // meaning we expect inner method to be in tail final position -> and throw an error otherwise. 
        def InnerFunction(j: Int, n:Int) : Int = {
            if (j%n==0) j
            else InnerFunction(j-1, n)
        }
        if (i<=n) 0
        else InnerFunction(i, n)   // return the first number less than or equal to i that is divisible by
    }
}

object Monomorphic {
// functions which act on only one type
// polymorphic - can templatise the type functions act on
// THis is also called PARAMETRIC POLYMORPHISM - to denote it from OO notino of inheritence/subtyping 

}

object StackOverflowAttempt {

    def x(i:Int) : Unit = {
        x(i)
    }

    def main(args: Array[String]) : Unit = {
        x(3);
    }

}


object AnonymousFunctions {
/**
* The f: A => Boolean is a function parameter
* Where exampleGeneric() is called, (x:Int) => x == 3  is a
* ANNOYMOUS FUNCTION of function literal
*/
def exampleGeneric[A](as: Array[A], f: A => Boolean) : Int = {
    @annotation.tailrec
    def loop(i: Int) : Int = {
        if (i>=as.length) -1
            else if (f(as(i))) i
        else loop(i+1)
    }
    loop(0)
}
/**

Annonymous functions, e.g. (a:String) => a == "Help"  -- is just syntati sugar for
val equalsHelp = new Function1[String, Boolean] {
    def apply(a:String) = a == "Help"
}
Where the apply is a keyword of scala ---
In this way functions are just objects like the rest of things in scala. 
*/
def main(args: Array[String]) : Unit = {
    println(exampleGeneric(Array(1,2,3,4), (x:Int) => x == 3))
}
}
