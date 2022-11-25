fun main() {
    val dividendo = mutableListOf<Int?>()
    val divisor = mutableListOf<Int?>()

    repeat(3) {
        println("Digite $it dividendo")
        dividendo.add(readln().toIntOrNull())
        println("Digite $it divisor")
        divisor.add(readln().toIntOrNull())
    }

    for (i in dividendo.indices) {
        var dividendo2 = dividendo[i]
        var divisor2 = divisor[i]

        try {
            val resultado = dividendo2?.div(divisor2!!)
            println("O resultado da divisão entre $dividendo2 e $divisor2 é: $resultado")
        } catch (e: ArithmeticException) {
            println("Não podemos fazer divisão por zero")
        } catch (e: NullPointerException) {
            println("Não podemos realizar a operaçãoi com nulos")
        } finally {
            println("Fim do laço")
        }

    }
}