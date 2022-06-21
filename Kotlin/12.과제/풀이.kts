//1
fun printN(getChar: Char, N: Int): Unit {
    for (i in 0 until N) {
        println(getChar)
    }
}
printN('a', 10)
println("-----------------------")

//2
fun factorial(value: Int): Int {
    var valueSum: Int = 0
    for (i in 1..value) {
        valueSum += i
    }
    return valueSum
}
println(factorial(10))
println("-----------------------")

// 3
fun findSevenSum(): Int {
    var sevenSum: Int = 0
    for (value in 0..100 step (7)) {
        sevenSum += value
    }
    return sevenSum
}
println(findSevenSum())
println("-----------------------")

// 4
fun make100(value: Int): Int {
    var numFor = value
    while (numFor < 100) {
        numFor++
    }
    return numFor
}

val numList = mutableListOf<Int>(10, 101, 100, 40)
for ((idx, num) in numList.withIndex()) {
    numList.set(idx, make100(num))
}
println(numList)
println("-----------------------")

//5
val scoreList = listOf<Int>(70, 71, 72, 77, 78, 79, 80, 82, 90, 99)

fun findTestResult(scoreList: List<Int>): MutableList<Boolean> {
    val resultList4 = mutableListOf<Boolean>()
    for (score in scoreList) {
        if (score >= 80) resultList4.add(true)
        else resultList4.add(false)
    }
    return resultList4
}
println(findTestResult(scoreList))
println("-----------------------")

// 6
fun diceResult(): Unit {
    var resultList5 = mutableListOf<List<Int>>()

    for (i in 1..6) {
        for (j in 1..6) {
            if (i + j == 6) resultList5.add(mutableListOf<Int>(i, j))
            else if (i + j > 6) break
        }
    }
    println(resultList5)
}
diceResult()
println("-----------------------")

// 7
fun eatCheck(wantCnt: Int, nowCnt: Int): Unit {
    var funNowCnt = nowCnt

    do {
        println("밥을 먹었다.")
        funNowCnt++
    } while (funNowCnt < wantCnt)
    println("배가 부르다.")
}
eatCheck(3, 5)
println("-----------------------")

// 8
fun makeGroup(
    firstGroup: MutableList<Char>,
    secondGroup: MutableList<Char>,
    n: Int
): List<List<Char>>? {
    if (firstGroup.size < n || secondGroup.size < n) {
        return null
    }
    var fFirstGroup: MutableList<Char> = firstGroup
    var fSecondGroup: MutableList<Char> = secondGroup

    fFirstGroup.removeAt(n - 1)
    fSecondGroup.removeAt(n - 1)

    return listOf<List<Char>>(fFirstGroup, secondGroup)
}

println(makeGroup(mutableListOf('A', 'B', 'C', 'D', 'E'), mutableListOf('A', 'B', 'C'), 2))
println("-----------------------")

// 9
fun getMultiplicationTable(n: Int): List<Int> {
    var resultList: MutableList<Int> = mutableListOf<Int>()
    for (i in 1..9) resultList.add(n * i)
    return resultList
}

println(getMultiplicationTable(3))
println("-----------------------")

// 10
fun makeMap(firstList: List<Int>, secondList: List<Int>): Map<String, List<Int>> {
    var resultMap: MutableMap<String, MutableList<Int>> = mutableMapOf<String, MutableList<Int>>(
        "짝수" to mutableListOf<Int>(),
        "홀수" to mutableListOf<Int>()
    )
    firstList.forEach {
        if (it % 2 == 0) resultMap["짝수"]!!.add(it)
        else resultMap["홀수"]!!.add(it)
    }
    secondList.forEach {
        if (it % 2 == 0) resultMap["짝수"]!!.add(it)
        else resultMap["홀수"]!!.add(it)
    }

    return resultMap
}

println(makeMap(listOf(1, 2, 3, 4), listOf(4, 5, 6, 9)))