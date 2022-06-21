// 기본자료형 타입 캐스팅
val num:Int = 10
val numString:String = num.toString()
val stringToNum:Int = numString.toInt()
println(numString)

val numberF: Double = 10.9
val numberFString:String = numberF.toString()
val numberFInt:Int = numberF.toInt()
println(numberFString)
println(numberFInt)
// -> to타입이 오는 함수를 이용해서 형 변환함

// 상송한 클래스 간의 캐스팅
// 상속 사용
open class Warrior1(var name: String, var power: Int, var type: String) { //부모 클래스, 슈퍼 클래스
    fun attack() {
        println("복잡한 코드 + 공격")
    }
}

// 주생성자가 슈퍼클래스 생성을 하는 경우
class DefenseWarrior1(
    name: String,
    power: Int
) : // 슈퍼클래스에서 var로 name이라는 변수를 선언했으므로 여기서는 var사용하면 오류남
    Warrior1(name, power, "고블린") { //서브 클래스가 부모 클래스의 생성을 책임지는 부분!
    fun defense() {
        println("방어")
    }
}

// is
// 타입 체크 (true or false)
// 스마트 캐스팅을 해줌
val warrior : Warrior1 = DefenseWarrior1("방어형 전사",100)
println(warrior.attack())
// warrior.defense() -> 불가능 -> defense 기능을 포기하고 warrior 타입이 되었기 때문!

if (warrior is DefenseWarrior1) {
    // 스마트 캐스팅
    // -> 내가 만든 warrior를 if 블럭 안에서는 defenseWarrior로 사용하게 해준다
    warrior.defense() // var 로 선언했을 시에는 스마트 캐스팅 안됨
}

// as
// - 지정한 타입으로 캐스팅을 시도하고, 불가능한 경우에는 예외 발생
val warrior2: DefenseWarrior1 = warrior as DefenseWarrior1
warrior2.defense()
val warrior3: DefenseWarrior1 = 10 as DefenseWarrior1 // -> 예외 발생!