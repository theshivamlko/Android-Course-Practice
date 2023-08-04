package com.example.myapplication.kotlin


fun main() {
    var teacher= Teacher()
     println(teacher.displayAbs())
}
  abstract class PersonAbstract {

      var age:Int =30
      fun display (){
          println("PersonAbstract Age $age")
      }

      abstract  fun displayAbs() // No body, write logic in inherited class

}

class  Teacher: PersonAbstract() {
    override fun displayAbs() {
        println("Teacher displayAbs $age")

    }


}