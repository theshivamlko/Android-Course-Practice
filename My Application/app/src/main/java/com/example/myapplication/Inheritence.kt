fun main() {

    val person: Person = Person("ShivamPerson", 21);
    person.eating()

    val player: Player = Player("ShivamPlayer", 23);
    player.play()
    player.eating()


}

open class Person(val name: String, val age: Int) {
    init {
        println("Person init")
    }
    fun eating() {
        println("$name is eating")
    }
}


class Player(private val name2: String, private val age2: Int) : Person(name2, age2) {
    init {
        println("Player init")
    }

    fun play() {

        println("$name is playing")
    }

}

class Teacher(val name: String) {
    fun teach() {
        println("$name is teaching")
    }
}

