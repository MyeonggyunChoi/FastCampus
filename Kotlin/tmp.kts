val a = mutableListOf<Int>(2,3,4,5,1,3,5)
val aa = mutableListOf<List<Int>>(listOf<Int>(1,2),listOf<Int>(3,6),listOf<Int>(2,4))
a.sort()
println(a)

val b = a.sortedDescending()
println(b)

val d = aa.sortedBy {
    -1 * it[1]
}
println(d)

val c = aa.map {
    it[0]*it[1]
}

println(c)








//val a:Array<Int> = arrayOf<Int>(1,0,2,3,4,5,0)
//val cntZero: (Int) -> Boolean = {
//    it == 0
//}
//println(a.count(cntZero))

//val a = 3
//when (a) {
//    1 ->{
//        println(1)
//    }
//    2,3 -> {
//        println(2)
//    }
//}

//val a = arrayOf(1,2,3)
//val b = IntArray(10,{0})
//val c = Array<Int>(10,{0})
//
//val aa = listOf(1,2,3)

//val bb = setOf(1,1,1,1)
//val cc = mapOf(2 to 2, Pair(1,3))
//println(cc)

//val a:String = "가나다"
//val b:MutableList<Char> = a.toMutableList()
//println(b)
//println(b.isEmpty())
//val b = a.split(",")
//println(b)
//
//val c:Int? = null
//if(c == null){
//    println(1)
//}else println(2)

//class Solution {
//    fun convert(s: String, numRows: Int): String {
//        var sList:MutableList<Char> = s.toMutableList()
//        var nowNumRow:Int = 0
//        var addCheck:Boolean = true
//        var resultList: MutableMap<Int,MutableList<Char>?> = mutableMapOf<Int,MutableList<Char>?>()
//        var resultStr:String = ""
//
//        while(!sList.isEmpty()){
//            if (resultList[nowNumRow] == null) {
//                resultList.set(nowNumRow,mutableListOf<Char>())
//            }
//            resultList[nowNumRow]?.add(sList[0])
//
//            if (nowNumRow == numRows-1) addCheck = false
//            else if (nowNumRow == 0) addCheck = true
//
//            if (addCheck){ //더하기
//                nowNumRow ++
//            } else {
//                nowNumRow --
//            }
//
//            sList.removeAt(0)
//        }
//        resultList.forEach{
//            println(it)
//            resultStr = resultStr + it.value?.joinToString("")
//        }
//        println(resultStr)
//        println("PAHNAPLSIIGYIR")
//        return ""
//    }
//}
//val sol:Solution = Solution()
//sol.convert("PAYPALISHIRING",3)

//val a:List<Int> = listOf<Int>(1,2,3)
//println(a.sum())
//println(a.fold(0){ acc, i ->
//    acc+i+1
//})

//val s:String = "abcd"
//for (i in s.indices) {
//    val index = s[i].toInt()
//    println(index)
//}