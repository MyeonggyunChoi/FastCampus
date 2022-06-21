// 오버로딩
class Sum {
    fun sum(): Int {
        return 10
    }

    //아래 것은 구분할 수가 없어 오류 발생
//    fun sum(): Int {
//        return 20
//    }

    fun sum(num1: Int): Int {
        return num1 + 10
    }

    fun sum(num1: Int, num2: Int): Int {
        return num1 + num2 + 10
    }
}

val sum = Sum()
println(sum.sum())
println(sum.sum(10))
println(sum.sum(10, 10))


// 오버라이딩
open class Warrior1(var name: String, var power: Int, var type: String) { //부모 클래스, 슈퍼 클래스
    open fun attack() {
        println("복잡한 코드 + 공격")
    }

    open fun getDefensePower(): Int {
        return 10
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

    override fun attack() {
//        super.attack() // 슈퍼 클래스의 함수
        println("오버라이딩한 공격!")
    }

    fun superattack() {
        super.attack()
    }

    override fun getDefensePower(): Int {
//        return super.getDefensePower()
        val defaultWarriorDefensePower: Int = super.getDefensePower()
        return defaultWarriorDefensePower + 5
    }
}

val defenseWarrior = DefenseWarrior1("Mike", 100)
defenseWarrior.attack()
defenseWarrior.superattack()
defenseWarrior.getDefensePower()