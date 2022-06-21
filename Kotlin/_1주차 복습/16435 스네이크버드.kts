fun main() = with(System.`in`.bufferedReader()) {
    val intputNL = readLine().split(" ").map { it.toInt() }
    var L = intputNL[1]

    val fluits: List<Int> = readLine().split(" ").map { it.toInt() }.sorted()

    for (fluit in fluits) {
        if (L >= fluit) L++
    }

    print(L)
}
main()

