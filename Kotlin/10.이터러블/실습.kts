// Range
val range1 = 1..10
val range2 = 1 until 10
val range3 = 'A'..'Z'
println(range1)
println(range2)
println(range3)

// Progression
val range4 = 1..10 step 2
val range5 = 10 downTo 1 step 2

val range6 = 10..0 step 1
for (i in range6){
    println(i)
}