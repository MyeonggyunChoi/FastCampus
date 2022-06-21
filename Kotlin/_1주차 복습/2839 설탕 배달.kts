/*
가장 큰 단위인 5만으로 나눠 담는 것
최소한의 3과 최대한의 5를 사용하여 나눠 담는 것
3으로 나눠 담는 것
나눠지지 않을 때
 */
fun main() = with(System.`in`.bufferedReader()) {
    var n = readLine()!!.toInt() //readLine()은 String 자료형으로 반환

    if (n % 5 == 0) println(n / 5)
    else {
        var p: Int = 0
        while (n > 0) {
            n -= 3
            p += 1
            if (n % 5 == 0) {
                p += n / 5
                print(p)
                break
            } else if (n == 1 || n == 2) {
                print(-1)
                break
            } else if (n == 0) {
                print(p)
                break
            }
        }
    }
}

main()