val number: List<Int> = listOf<Int>(1, 2, 3)
try {
    number.get(5)
} catch (exception: java.lang.Exception) {
    println(exception)
}

try {
    number.get(5)
} catch (exception: ArrayIndexOutOfBoundsException) {
    println("예외발생")
} finally {
    println("final")
}