val numbers = intArrayOf(5,10,15)

// 값만 필요할 때
for (number in numbers) {
    println(number)
}
println("-------------")
// 값과 인덱스가 필요할 때
for ((index,value) in numbers.withIndex()) {
    println("$index:$value")
}
println("-------------")
// 인덱스만 필요할 때
for (index in numbers.indices) {
    println(index)
}
println("-------------")



numbers.forEach {
    println(it)
}
println("-------------")

numbers.forEachIndexed { index, value ->
    println("$index:$value")
}
println("-------------")
