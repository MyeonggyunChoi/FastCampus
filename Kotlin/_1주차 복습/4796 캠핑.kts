//강산이는 이제 막 28일 휴가를 시작했다. 이번 휴가 기간 동안 강산이는 캠핑장을 며칠동안 사용할 수 있을까?
//
//강산이는 조금 더 일반화해서 문제를 풀려고 한다.
//
//캠핑장을 연속하는 P일 중, L일동안만 사용할 수 있다. 강산이는 이제 막 V일짜리 휴가를 시작했다. 강산이가 캠핑장을 최대 며칠동안 사용할 수 있을까? (1 < L < P < V)

fun main() = with(System.`in`.bufferedReader()) {
    var input:String = ""
    var cnt = 1
    while(true){
        input = readLine()
        if (input == "0 0 0"){
            println("rkrkkrkrk")
            break
        }
        println("Case $cnt: ${findAnser(input)}")

        cnt ++
    }
}

fun findAnser(input:String):Int{
    val inputList = input.split(" ").map { it.toInt() }
    val P:Int = inputList[0]
    val L:Int = inputList[1]
    val V:Int = inputList[2]

    if (V - P*(V/P) >= L) return L * (V / P) + L
    else return V - P * (V / P) + L * (V / P)
}

main()
