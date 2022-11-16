fun main() {
    var num: Int
    var maior = 0
    var menor = 0

    for (i in 1..10) {
        print("Digite o " + i + "º número: ")
        num = readln().toIntOrNull() ?: 0

        if (i == 1) {
            maior = num
            menor = num
        }

        if (num > maior) {
            maior = num
        } else if (num < menor) {
            menor = num
        }
    }

    println("O maior número é $maior")
    println("O menor número é $menor")
}