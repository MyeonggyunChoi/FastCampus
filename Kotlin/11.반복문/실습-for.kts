for (value in 0..10) {
    println(value)
}
println("---------------")
for (value in 20..10) { // 이렇게 하면 값 안나옴
    println(value)
}
println("---------------")
for (value in 0..5 step (2)) {
    println(value)
}
println("---------------")
for (value in 10 downTo 0) {
    println(value)
}
println("---------------")


val numbers = listOf<Int>(0,1,2,3,4)
for (number in numbers) {
    println(number)
}
println("---------------")
for (i in 0..numbers.size-1) {
    println(numbers[i])
}
println("---------------")
for (i in 0 until numbers.size) {
    println(numbers[i])
}
println("---------------")

for ((index,number) in numbers.withIndex()){ // withIndex() : index와 value를 함께 가져오는 함수
    println(""+index + "  " + number)
}
println("---------------")


numbers.forEach{
    println(it)
}
println("---------------")

numbers.forEach{ num ->
    println(num)
}
println("---------------")