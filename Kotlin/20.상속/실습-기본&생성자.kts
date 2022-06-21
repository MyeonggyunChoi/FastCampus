// 상속 사용 전
class Warrior() {
    fun attack() {
        println("복잡한 코드 + 공격")
    }
}

class DefenseWarrior() {
    fun attack() {
        println("복잡한 코드 + 공격")
    }

    fun defense() {
        println("방어")
    }
}

class HardAttackWarrior() {
    fun attack() {
        println("복잡한 코드 + 공격")
    }

    fun hardAttack() {
        println("강하게 공격")
    }
}

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

// 주생성자가 없는 경우
class HardAttackWarrior1 : Warrior1 {
    var bonusPower: Int = 0

    constructor(name: String, power: Int, bonusPower: Int) : super(name, power, "골렘") {
        this.bonusPower = bonusPower
    }

    fun hardAttack() {
        println("${bonusPower}만큼 강하게 공격")
    }
}


val defenseWarrior1: DefenseWarrior1 = DefenseWarrior1("smart goblin", 100)
defenseWarrior1.defense()
defenseWarrior1.attack()    

val hardAttackWarrior: HardAttackWarrior1 = HardAttackWarrior1("멍청한 골렘", 100, 20)
hardAttackWarrior.attack()
hardAttackWarrior.hardAttack()