// getter / setter
class Book() {
    var title: String = "모름"
        get() {
            return field // title을 가르킴
        }
        set(value) {
            field = value // title을 가르킴
        }

    var subtitle: String = "모름"
}

val book = Book()
println(book.title)
book.title = "제목 변경"
println(book.title)

// lateinit
class Book2() {
    lateinit var title: String

    fun nextPage() {
        if (::title.isInitialized) {
            println("페이지가 넘어감")
        } else {
            println("초기화 필요")
        }
    }
}

val book2 = Book2()
book2.nextPage()
book2.title = "book name"
book2.nextPage()


// lazy
// - 호출시점에 by lazy 정의에 의해서 초기화 수행
// - val 에서만 사용 가능
class Book3{
    val title : String by lazy {
        // 본문 -> 다른 작업도 할 수 있지만, 반드시 프로퍼티를 초기화 시켜주는 작업을 해야 함
        println("lazy 초기화")
        "책 제목"
    }
}

val book3 = Book3()
println(book3.title)