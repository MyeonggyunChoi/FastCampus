// 1
class Calculator {
    var result: Int = 0
        set(value) {
            field = value
            println("중간 계산 값 : " + field)
        }

    fun sum(inputNum: Int) {
        result += inputNum
//        printResult()
    }

    fun minus(inputNum: Int) {
        result -= inputNum
//        printResult()
    }

    fun multiply(inputNum: Int) {
        result *= inputNum
//        printResult()
    }

    fun divide(inputNum: Int) {
        result /= inputNum
//        printResult()
    }

//    fun printResult() {
//        println(result)
//    }
}

val calculator = Calculator()
calculator.sum(5)
calculator.multiply(10)
calculator.minus(20)
calculator.divide(3)

println("----------------------")
//2
class Caculator2(resultVal: Int = 0) {
    var resultVal: Int

    init {
        this.resultVal = resultVal
    }

    fun calcuate(operator: Char, inputVal: Int): Unit {
        when (operator) {
            '+' -> resultVal += inputVal
            '-' -> resultVal -= inputVal
            '*' -> resultVal *= inputVal
            '/' -> resultVal /= inputVal
            else -> {
                println("잘못된 연산입니다.")
                return
            }
        }
        println(resultVal)
    }
}

val calculator2 = Caculator2(5)
calculator2.calcuate('+', 5)
calculator2.calcuate('*', 10)
calculator2.calcuate('-', 20)
calculator2.calcuate('/', 4)
calculator2.calcuate('!', 10)

println("----------------------")
//3
class Caculator3 {
    var result: Int = 0

    fun calculate(input: String) {
        var inputList: List<String> = input.split(",")
        var operator: Char
        var value: Int
        for (i in inputList) {
            operator = i[0]
            value = i.slice(1..i.length - 1).toInt()

            when (operator) {
                '+' -> result += value
                '-' -> result -= value
                '*' -> result *= value
                '/' -> result /= value
                else -> {
                    println("잘못된 연산입니다.")
                    return
                }
            }
        }
        println(result)
    }
}

val calculator3 = Caculator3()
calculator3.calculate("+3,+5,-2,/5,*15")