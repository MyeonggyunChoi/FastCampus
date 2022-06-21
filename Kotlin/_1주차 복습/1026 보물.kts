fun main() = with(System.`in`.bufferedReader()) {
    val N: Int = readLine().toInt()
    var A: List<Int> = readLine().split(" ").map { it.toInt() }.toMutableList().sorted()
    var B: List<Int> = readLine().split(" ").map { it.toInt() }.toMutableList().sortedBy { -1*it }
    var S: Int = 0

    for (i in 0 until A.size){
        S += A[i]*B[i]
    }
    print(S)
}

main()
