fun plusNumbers(firstNum: Int, secondNum: Int): Int {
    return firstNum + secondNum
}
println(plusNumbers(firstNum = 10, secondNum = 30))
println(plusNumbers(10, 20))

fun plusNumbersWithDefalt(firstNum: Int = 10, secondNum: Int): Int {
    return firstNum + secondNum
}
println(plusNumbersWithDefalt(secondNum = 20))

fun plusNumbersWithNoReturn(firstNum: Int, secondNum: Int): Unit {
    println(firstNum + secondNum)
//    return firstNum + secondNum // 반환값이 Unit일 때는 return이 있으면 안됨
}

fun plusNumbersWithNoReturn1(firstNum: Int, secondNum: Int) {
    println(firstNum + secondNum)
//    return firstNum + secondNum // 반환값이 Unit일 때는 return이 없거나 return만 있어야 함. return Unit은 동작은 하나 선호되지 않음
}

fun plusNumbersWithNoReturn2(firstNum: Int, secondNum: Int) {
    println(firstNum + secondNum)
    return
}
plusNumbersWithNoReturn(100, 200)
plusNumbersWithNoReturn(100, 300)
plusNumbersWithNoReturn(100, 400)

fun shortPlusNumbers(firstNum:Int,secondNum:Int) = firstNum + secondNum
println(shortPlusNumbers(10,30))

fun plusMultipleNumbers(vararg numbers: Int):Unit{
    for (number in numbers){
        println(number)
    }
}
plusMultipleNumbers(10,20,30,40)
