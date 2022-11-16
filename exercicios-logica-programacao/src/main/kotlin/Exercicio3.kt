fun main() {
    val lista: MutableList<Int> = mutableListOf()
    var num: Int
    for (i in 1..5) {
        print("Digite o " + i + "º número: ")
        num = readln().toIntOrNull() ?: 0
        lista.add(num)
    }
    print(lista.reversed())
}