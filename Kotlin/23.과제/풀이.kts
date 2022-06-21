//1.
open class Entity constructor(name: String, life: Int, attackPower: Int, defencePower: Int) {
    val name: String
    var life: Int = life
//        set(value) { // 라이프가 바뀔 때마다 생존여부 확인 후 화면에 표시
//            field = value
//            if (isAlive()) println(this.name + " 아직 살아있습니다. 체력:" + this.life)
//            else println(this.name + "가 죽었습니다.")
//        }

    val attackPower: Int
    val defencePower: Int

    init {
        this.name = name
        this.life = life
        this.attackPower = attackPower
        this.defencePower = defencePower
    }

    fun isAlive(): Boolean {
        if (this.life > 0) println(this.name + " 아직 살아있습니다. 체력:" + this.life)
        else println(this.name + "가 죽었습니다.")

        return (this.life > 0)
    }

    fun damaged(power: Int): Unit {
        if (power >= this.defencePower) this.life -= power - defencePower
        else println("방어력이 높아 데미지를 받지 않습니다.")

        isAlive()
    }

    fun attack(damagedEntity: Entity): Unit { // 공격
        if (damagedEntity.life <= 0) { // 이미 죽은 경우는 넘기기
            println(damagedEntity.name + "은 이미 죽어있습니다.")
            return
        }

        println(this.name + " 가 공격합니다.")
        damagedEntity.damaged(this.attackPower)

        if (this is Human) { // 스마트 케스팅
            if (this.type == "Warrior") {
                if (damagedEntity.life <= 0) {
                    this.cntKill += 1
                    if (this.cntKill == this.pointLevelUp) {
                        this.levelUp() // Warrior가 몬스터를 죽였으므로 레벨 업
                    }
                }
            }
        }

    }
}

class Human constructor(
    name: String,
    life: Int,
    attackPower: Int,
    defencePower: Int,
    cntKill: Int = 0,
    energy: Int,
    type: String //Worrior인지, Kight인지 설정
) :
    Entity(name, life, attackPower, defencePower) {
    var type: String

    // Warrior에서 필요한 부분
    var cntKill: Int
    val pointLevelUp: Int = 5 // 몇마리를 죽여야 레벨업이 가능한지

    // Knight에서 필요한 부분
    var energy: Int
    val hardAttackPower: Int = this.attackPower * 5

    init {
        this.cntKill = cntKill
        this.type = type
        this.energy = energy
    }

    fun hardAttack(damagedEntity: Entity): Unit {
        if (this.type == "Warrior") {
            println("해당 기술은 기사만 사용할 수 있습니다.")
            return
        }
        if (checkEnerge()) {
            println(this.name + "가 Hard Attack을 실행합니다.")
            damagedEntity.damaged(this.hardAttackPower)
            this.energy -= 3
        } else println("energe shortage")
    }

    fun checkEnerge(): Boolean {
        return (this.energy >= 3)
    }

    fun levelUp(): Unit {
        this.type = "Knight"
        println(this.name + "이 Knight로 승급하였습니다.")
    }
}

//class Knight constructor(
//    name: String,
//    life: Int,
//    attackPower: Int,
//    defencePower: Int,
//    energy: Int
//) :
//    Entity(name, life, attackPower, defencePower) {
//    var energy: Int
//    val hardAttackPower: Int = this.attackPower * 5
//
//    init {
//        this.energy = energy
//    }
//
//    fun hardAttack(damagedEntity: Entity): Unit {
//        if (checkEnerge()) {
//            println(this.name + "가 Hard Attack을 실행합니다.")
//            damagedEntity.damaged(this.hardAttackPower)
//            this.energy -= 3
//        } else println("energe shortage")
//    }
//
//    fun checkEnerge(): Boolean {
//        return (this.energy >= 3)
//    }
//}

class Monster constructor(
    name: String,
    life: Int,
    attackPower: Int,
    defencePower: Int
) : Entity(name, life, attackPower, defencePower) {

    init {
        println(this.name + " 생성")
    }
}

var monster: Monster = Monster("Monster", 50, 10, 5)
val warrior: Human = Human("Warrior", 50, 20, 5, 0, 0, "Warrior")
val knight: Human = Human("Knight", 80, 30, 10, 0, 10, "Knight")

monster.isAlive()
for (j in 0..4) {
    monster = Monster("Monster", 50, 10, 5)
    for(i in 0..3) {
        warrior.attack(monster)
    }
}

monster = Monster("Monster", 50, 10, 5)
knight.hardAttack(monster)