//상근이는 2863번에서 표를 너무 열심히 돌린 나머지 5와 6을 헷갈리기 시작했다.
//상근이가 숫자 5를 볼 때, 5로 볼 때도 있지만, 6으로 잘못 볼 수도 있고, 6을 볼 때는, 6으로 볼 때도 있지만, 5로 잘못 볼 수도 있다.
//두 수 A와 B가 주어졌을 때, 상근이는 이 두 수를 더하려고 한다. 이때, 상근이가 구할 수 있는 두 수의 가능한 합 중, 최솟값과 최댓값을 구해 출력하는 프로그램을 작성하시오.

fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine()
    val inputList:MutableList<String> = input.split(" ").toMutableList()
    var firstMin = inputList[0].toInt()
    var firstMax = inputList[0].toInt()
    var secondMin = inputList[1].toInt()
    var secondMax = inputList[1].toInt()

    if (("5" in inputList[0]) || ("6" in inputList[0])) {
        firstMin = inputList[0].replace('6','5').toInt()
        firstMax = inputList[0].replace('5','6').toInt()
    }

    if (("5" in inputList[1]) || ("6" in inputList[1])) {
        secondMin = inputList[1].replace('6','5').toInt()
        secondMax = inputList[1].replace('5','6').toInt()
    }

    print("${firstMin+secondMin} ${firstMax+secondMax}")
}
main()


