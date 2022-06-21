// ?
val num: Int = 10 // Non-Nullable Int
val num1: Int? = null // Nullable Int

// !!
// 정말 필요한 경우에만 사용 권장
//val nullableNumberList: List<Int?> = listOf<Int?>(1, 2, 3, null, null)
val nullableNumberList: List<Int?> = listOf<Int?>(1, 2, 3, 4, 5)
var result: Int = 0
nullableNumberList.forEach {
    result += it!!
}

// ?. (safe call)
//val text:String? = "text"
val text: String? = null
println(text?.length)
//println(text.length) ==> 에러 발생!

// !!.
// 개발자가 null 이 아님을 보장하고 실행하게 하는 것
// 이 것도 되도록 사용하지 않는 것 추천

// as?
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
) //: Warrior1(name, power, "고블린")
{ //서브 클래스가 부모 클래스의 생성을 책임지는 부분!
    fun defense() {
        println("방어")
    }
}

// is
// 타입 체크 (true or false)
// 스마트 캐스팅을 해줌
val defenseWarrior: DefenseWarrior1 = DefenseWarrior1("방어형 전사", 100)
//val warrior = defenseWarrior as Warrior1 --> 상속을 끊었을 때, 변환이 안됨으로 에러 발생 (캐스팅 불가능)
val warrior = defenseWarrior as? Warrior1 // as?를 써서 에러 발생 X
println(warrior)

// ?: 엘비스 연산
val text2: String? = "123"
val nullText: String? = null

var len1: Int = if (text2 != null) text2.length else 0
var len2: Int = text2?.length ?: 0 // 위의 if문과 동일한 기능 실행됨
println(len1)
println(len2 )