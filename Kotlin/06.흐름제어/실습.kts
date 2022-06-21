val number1: Int = 0
if (number1 == 10) {
    println("10")
} else if (number1 == 20) {
    println(20)
} else {
    println(0)
}

if (number1 == 10) println("10")
else if (number1 == 20) println(20)
else println(0)

val number2: Int = 30
val number3: Int = if (number2 > 20) 40 else 50
println(number3)

val number5: Int = 5
when (number5) {
    5 -> {
        println(5)
    }
    else -> {
        println("rkrk")
    }
}

when (number5) {
    4 -> println(5)
    else -> println("rkrk")
}