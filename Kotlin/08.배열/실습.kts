var array1 = arrayOf(true, "Hi", 10, 2.2, null)
var array2 = arrayOf<Int>(10, 2)

var array3 = Array(10,{0})
//array3[0] = 1
// array3[1] = "가나다" -> 이거 안됨
var array4 = IntArray(10,{0})

var array5 = Array(10,{""})
var array6 = Array<Int>(10,{0})

println(array6[0])
array6[0] = 1
array6.set(1,100)
println(array6[0])
println(array6.get(1))

