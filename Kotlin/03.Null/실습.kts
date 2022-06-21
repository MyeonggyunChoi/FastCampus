val number: Int? = null
val number1: Int? = 10
val number2: Int? = 1
//val number4: Int? = number1 + number2 // 오류 발생 -> number1이 정수일지, null일지 몰라서 에러 발생하는 것
val number4: Int? = number1!! + number2!!
println(number4)

if (null == g 5) {
    println("same")
} else println("not smae")

if (null == null) {
    println("same")
} else println("not smae")