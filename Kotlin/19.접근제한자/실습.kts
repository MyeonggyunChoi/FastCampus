// Scope
var number: Int = 1

fun changeNumber() {
    var interalNumber: Int = 2
    number = 20 // 하위 스코프에서 상위 멤버에 접근할 수 있다.
    var number: String = "재정의" // 하위 스코프에서 상위 멤버를 재정의할 수 있다. => 사실 위의 number 변수와 해당 변수는 아무 상관이 없음
    println(number)
}

changeNumber()
println(number)

// internalNumber = 3 에러 발생( 상위 스코프에서 하위 스코프 멤버에 접근 불가능 )
// 하위 -> 상위 접근은 가능
// 상위 -> 하위 접근은 불가능 -> 사실상 하위는 독립적


// 접근제한자
class Numbers(private var number:Int = 10){
    fun chageNumber(){
        this.number = 100 //Numbers라는 스코프 내에 있기 떄문에 number에 접근/변경 가능
    }

    fun whatIsNumber():Int{
        return this.number
    }
}

val numbers = Numbers()
numbers.chageNumber()
println(numbers.whatIsNumber())

// private 이기 때문에 아래 두개 다 불가능
//println(numbers.number)
//numbers.number = 100