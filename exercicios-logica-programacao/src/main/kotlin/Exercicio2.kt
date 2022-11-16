fun main() {
    print("Digite um número: ")
    val num = readln().toIntOrNull() ?: 0
    println("Tabuada do $num")

    for (i in 1..10) {
        println("$i x $num = " + i * num)
    }
}