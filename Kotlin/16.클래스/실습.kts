// 클래스를 선언하는 방법
class Person {}

// 생성자
// - 주 생성자
//      - 클래스 이름 옆에 괄호로 둘러싸인 코드
//      - 클래스를 통해서 객체를 만드는데 필요한 재료들 적어줌
//          - 재료이름(변수명) : 재료타입(변수타입)
//      - 반드시 한 개만 존재
//      - constructor 키워드 생략 가능

// - 주 생성자 -> 풀버전 ( 생략 x )
class User1 constructor(name: String) { // 클래스 네이밍은 대문자로 시작
    val userName: String // 클래스 속성(프로퍼티,property)는 init 블럭에서 초기화 된다

    init { // 클래스가 생성될 때 호출됨

        userName = name
        println(userName)
    }
}

// 클래스를 호출하는 방법 -> 클래스를 통해 객체를 만드는 방법
// 클래스를 호출 -> 인스턴스화 (Instance)
// 객체 -> Object, Instance
val user = User1("홍길동")

// - 주 생성자 -> init을 생략하는 방법
class User2 constructor(name: String) {
    val userName: String = name
}

// - 주 생성자 -> constructor를 생략하는 방법
class User3(name: String) {
    val userName: String = name
}

// - 주 생성자 -> 기본값
class User4(name: String = "익명") {
    val userName: String = name
}

// - 주 생성자 -> 속성 여러개
class User5(name: String, age: Int) {
    val name: String
    val age: Int

    init {
        this.age = age // this는 클래스 자체를 의미함
        this.name = name
    }
}

val user5 = User5("mama", 50)
println("${user5.age},${user5.name}")
// -> . --> ~의 라는 의미

// - 주 생성자 -> 모든 걸 생략하는 방법
class User4(val userName: String) {
}

// 부생성자 (Secondary Constructor)
// - constuctor 키워드를 생략할 수 없다!
// - 주생성자에는 객체를 만들기 위한 필수 조건이 있다면, 부생성자에는 객체를 만들기 위한 부수 조건이 있다.
// - 부생성자에는 주생성자에서 필요한 조건을 포함하고 있어야 한다. (파라미터를 포함해야 함)
// - 부생성자는 주생성자에게 생성을 위임해야 함
// - 부생성자는 여러 개일 수 있음
class User6 constructor(name: String) {
    var age: Int = 0
    var name: String

    init {
        println("init")
        this.name = name
    }

    // 부생성자는 클래스명 우측에 올 수 없다. -> 클래스의 본문에 있어야 함다
    constructor(name: String, age: Int) : this(name) {
        println("second")
        this.age = age
    }
}

val user6 = User6("abc")
println("${user6.age},${user6.name}")

val user7 = User6("ddf",30)
println("${user7.age},${user7.name}")

// 실행순서
// 부생성자 호출 -> 부생성자 안에 있는 주생성자 호출 -> init 블럭 호출 -> 부생성자 본문 실행
// 클래스 속성에서 초기화를 시캬주지 않아도 괴는 이유
//  - 초기화 블록에서 초기화를 보장해주기 때문 -> 클래스가 생성될 떄, 초기화블록은 무조건 실행된다
//  - 초기화 블록에 없는 속성은 선언할 떄, 값을 할당해줘야 한다


// 주생성자가 없는 것처럼 보이고 부생성자만 있을 때, 부생성자가 주생성자처럼 보이지만, 부생성자가 맞음
// 주생성자는 코틀린이 자옫으로 만들어줌
class User7 {
    val age: Int
    val name: String

    constructor(age:Int,name:String){
        this.age = age
        this.name = name
    }
}

