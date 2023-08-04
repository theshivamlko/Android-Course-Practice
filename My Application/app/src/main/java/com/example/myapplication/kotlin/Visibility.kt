package com.example.myapplication.kotlin


fun main() {
    val person: Person = Person();
    println(person.name)
  //  println(person.salary) // private Error

    val visibility2: Visibility2 = Visibility2();
    println(visibility2.name)
    println(visibility2.hairColor)


}
open class Person {
   open var name :String ="Shivam" // public ,default
    // open makes non-final

     private var salary:Float=30000.56f
     open    var hairColor ="Brown"
}