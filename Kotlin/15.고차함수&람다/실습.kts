fun addTwoNumbers(num1: Int, num2: Int): Int {
    return num1 + num2
}

// 고차함수
fun addTenNine(func: (Int, Int) -> Int) {
    val result: Int = func(10, 9)
    println(result)
}

addTenNine(::addTwoNumbers)

// 람다
// 풀버전
val addTenNine2: (Int, Int) -> Int = { num1: Int, num2: Int ->
    num1 + num2
}
addTenNine(addTenNine2) // 람다 함수를 인자로 사용하는 경우에 ::사용할 필요 없음

// 생략버전 1
val addTenNine3: (Int, Int) -> Int = { num1, num2 ->
    num1 + num2
}

// 생략버전 2
val addTenNine4 = { num1: Int, num2: Int ->
    num1 + num2
}

// 너무 간단한 경우
addTenNine {num1, num2 -> num1+num2}

// 파라미터 없는 경우
val addTenNine5 () -> Int = {
    10+9
}

// 파라미터 하나인 걍우
val addTanNine:(Int) -> Int = {
    10+9
}