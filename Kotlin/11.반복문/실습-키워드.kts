//break
for (i in 1..3) {
    println("i:$i")
    for (j in 1..3) {
        if (j == 2) {
            break
        }
        println("j:$j")
    }
}
println("------------------")
//continue
for (i in 1..3) {
    println("i:$i")
    for (j in 1..3) {
        if (j == 2) {
            continue
        }
        println("j:$j")
    }
}
println("------------------")
//return
fun tmp(): Unit {
    for (i in 1..3) {
        println("i:$i")
        for (j in 1..3) {
            if (j == 2) {
                return
            }
            println("j:$j")
        }
    }
}

tmp()
//break
for (i in 1..3) {
    println("i:$i")
    for (j in 1..3) {
        if (j == 2) {
            break
        }
        println("j:$j")
    }
}
println("------------------")
//break와 label
println("break_label")
loop1@for (i in 1..3) {
    println("i:$i")
    for (j in 1..3) {
        if (j == 2) {
            break@loop1
        }
        println("j:$j")
    }
}
println("------------------")
//continue와 label
println("break_label")
loop2@for (i in 1..3) {
    println("i:$i")
    for (j in 1..3) {
        if (j == 2) {
            continue@loop2
        }
        println("j:$j")
    }
}
println("------------------")

// foreach + label
// foreach는 continue와 break 사용 불가능!!
listOf(1,2,3).forEach loop@{
    if (it == 2) return@loop
    println(it)
}

