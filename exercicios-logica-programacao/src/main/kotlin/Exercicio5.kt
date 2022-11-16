fun main() {
    println("Informe um número:")
    val num: Int = readln().toInt()

    val list = mutableListOf<Int>()
    for (i in 2..num) {
        if ((2 until i).none { i % it == 0 })
            list.add(i)
    }

    println(list)
}