package com.example.myapplication


fun main() {

    var lam = { -> 2 * 2 }

    println(lam)
    println(lam.javaClass)
    println(lam.invoke()) // call
    println(lam())    // call
    println(lam) // variable

    var lam2 = { x: Int -> x * 2 }
    println(lam2(10))    // call
    println(lam2)    // call

    var lam3: (Int, Boolean) -> String = { x, b -> "$x $b...." }
    println(lam3(5, false))    // call

    var lam4: (Int, Boolean, List<Int>) -> String = { x, b, list -> "$x $b....$list" }
    println(lam4(5, false, listOf(1, 2, 3, 4)))    // call

    var lam6: () -> Unit = { -> 5 * 2 }
    println(lam6())    // call

    println({ a: Int, b: Int -> a * b }(4, 5))    // call


    // Anonymous Function
    println("Anonymous")    // call
    var anon1 = fun(x: Int, b: String, list: List<Int>): String {
        return "$x $b $list"
    }

    println(anon1.invoke(10, "anon1", listOf(1, 2, 3)))    // call

    var anon3 = fun(x: Int, b: String, list: List<Int>): String = "$x $b $list"
    println(anon3.invoke(10, "anon3", listOf(1, 2, 3)))

    var anon4 = fun(x: Int, b: String, list: List<Int>) = println("anon4")
    println(anon4.invoke(10, "anon4", listOf(1, 2, 3)))    // call

    var anon5 = fun() = println("anon5")
    println(anon5.invoke())    // call

    println("HOF")    // call

    val add = { a: Int, b: Int -> a + b }
    hof(add)

    hof2("Shivam", { a: Int, b: Int -> a + b })
    hof2("Shivam") { a: Int, b: Int -> a + b }

    hof3 {
        println("Hi $it")
    }

    higherfunc(::add2)

    var num = listOf<Int>(1, 2, 3, 4, 5, 6)
    println(num.filter { it > 3 })
    println(num.filter { n -> n > 5 })

    val sub = fun(a: Int, b: Int): Int {
        return a - b;
    }
    hof4("Subtract", fun(a: Int, b: Int): Int {
        return a - b;
    })


    println(hof6()())
    println(hof7(::message))

}

fun add2(a: Int, b: Int): Int {
    var sum = a + b
    return sum
}

fun higherfunc(addfunc: (Int, Int) -> Int) {
    // invoke regular function using local name
    var result = addfunc(3, 6)
    println("The sum of two numbers is: $result")
}

fun hof(addition: (Int, Int) -> Int) {
    val result = addition(4, 5)
    println("hof $result")
}


fun hof2(name: String, addo: (Int, Int) -> Int) {
    val result = addo(4, 5)
    println("hof2 $name $result")
}

fun hof3(name: (String) -> Unit) {
    name("hof3")
}


fun hof4(name: String, subtract: (Int, Int) -> Int) {
    var res = subtract(10, 5)
    val msg = fun(): String {
        return "abc"
    }
    println("hof4 $res")
    println("hof4 ${subtract.javaClass}")
    println("hof4 ${msg.javaClass}")
}

// higher-order function definition

fun hof6():() -> String {
    return ::message;
}
fun message():String{
    return  "Hi"
}

fun hof7(regFun:() -> String) {
    println(regFun);
}


class Lambda {


}