//List
val number = listOf<Int>(1,2,3)
val number2 = mutableListOf<Int>(1,2,3)
number2[0] = 1000
println(number2[0])

val number3 = setOf<Int>(1,1,1,3)
val number4 = mutableSetOf<Int>(1,1,1,3)
println(number3)

val number5 = mapOf<Int,String>(1 to "o", 2 to "t")
val number6 = mutableMapOf<Int,String>(Pair(1, "o"), 2 to "t")