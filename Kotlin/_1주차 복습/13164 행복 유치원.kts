fun main() = with(System.`in`.bufferedReader()) {
    val inputNK = readLine().split(" ").map { it.toInt() }
    val N = inputNK[0]
    val K = inputNK[1]

    var childrenList = readLine().split(" ").map { it.toInt() }
    var diffList = mutableListOf<Int>()

    var preIndex = 0
    for (index in 1..N - 1) {
        diffList.add(childrenList[index] - childrenList[preIndex])
        preIndex = index
    }

    diffList = diffList.sorted().reversed().toMutableList()

    for (i in 1..K-1) {
        diffList.removeAt(0)
    }

    println(diffList.sum())
}

main()

