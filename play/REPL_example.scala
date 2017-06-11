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

def printGiven(x: Int)  = {
    println(x)
}

def main(args: Array[String]) : Unit = {
    println(33)
}
}
