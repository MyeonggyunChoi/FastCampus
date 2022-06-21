class Peson(var name: String? = null, var age: Int? = null) {}

// apply
// - 적용하다
// - 객체를 초기화할 때 사용하면 좋음

val gildong = Peson().apply{
    name = "길동"
    age = 20
}

// also
// 유효성 검사할 떄 좋음
// 수신된 객체의 속성을 변경하지 않고 사용할 때 좋음
val gildong2 = Peson("victor").also{
//    nameIsGildong(it.name)
}

// run
// - 기본적으로 apply와 동일
// - 스코프의 마지막 줄을 리턴함 -> 특정 계산 결과가 필요한 경우
val ageAfter10year = Peson("Gildong",10).run{
    age!! +10
}
println(ageAfter10year)

// with
// - run과 기능은 동일
// - nullable 타입을 받지 못함
val ageAfter10year2 = with(Peson("Gildong",10)){
    age!!+10
}
println(ageAfter10year2)

// let
// - 기본적으로 also와 동일
// - 스코프의 마지막줄을 리턴
// - 물음표를 사용해서 객체?.let --> 이런 식으로 해서 객체가 null이 아니면 아래 내용을 실행하겠다. 이런 식으로 사
val gildong5 = Peson("victor").let{
//    nameIsGildong(it.name)
}