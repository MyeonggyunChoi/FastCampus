interface Tiger {
    fun bite()
    fun goToBox()
}

class BackDoSanTiger : Tiger {
    override fun bite() {

    }

    override fun goToBox() {

    }
}

val backDoSanTiger: BackDoSanTiger = BackDoSanTiger()
backDoSanTiger.bite()
backDoSanTiger.goToBox()


interface Person { //생성자 쓸모 없음
    // 멤버들의 구현부가 없다
    var dress: String
    fun eat()
    fun sleep() {
        println("잠")
    }
}

class Student : Person {
    override var dress: String
        get() = "옷"
        set(value) {}

    override fun eat() { // 자바와는 다른 코틀린의 특징! 인터페이스에서도 구현부를 넣을 수 있음
        println("식사")
    }
}

class Teacher : Person {
    override var dress: String
        get() = "교사"
        set(value) {}

    final override fun eat() { //final -> 이 클래스를 상속하는 클래스에서 override를 못하게 한다.
        println("식사")
    }
}

class GoodStudent: Student(){

}

val student: Student = Student()
val teacher: Teacher = Teacher()

student.sleep()
teacher.sleep()