import kotlin.system.exitProcess

fun main() {
    println("Bem vindo a E-Padoca!")
    menuPrincipal()

}

fun menuPrincipal() {
    val menuPrincipal = listOf("Pães", "Doces")
    var valorPaes = mutableListOf<String>()
    var valorDoces = 0

    println("Selecione uma opcão")

    for (i in menuPrincipal.indices) {
        println("${i + 1}.........${menuPrincipal[i]}")
    }
    println("0.........Finalizar")

    var opcaoMenuPrincipal: Int = readLine()?.toInt() ?: 0

    when (opcaoMenuPrincipal) {
        1 -> valorPaes = menuPaes()
        2 -> valorDoces = menuDoces()
        0 -> finalizarCompra(valorPaes, valorDoces)
        else -> "Opcão inválida"
    }

    print(valorPaes)
}

fun finalizarCompra(valor1: MutableList<String>, valor2: Int) {
    if (valor1.isEmpty() && valor2 == 0) {
        print("Deseja mesmo cancelar? (S/N) ")
        if (readLine().toString() == "S") {
            exitProcess(0)
        } else {
            menuPrincipal()
        }
    }
}

fun menuPaes(): MutableList<String> {
    var valor = 0
    var listaDePaes: MutableList<String> = mutableListOf()
    val menuPaes = listOf("Pão francês", "Pão de milho")
    val precoPaes = listOf(1.00, 2.00)

    do {
        println("Escolha o item")
        for (i in menuPaes.indices) {
            println("${i + 1} - ${menuPaes[i]}......R$ ${precoPaes[i]}")
        }
        println("0.........Voltar")

        val escolha: Int = readLine()?.toInt() ?: 0

        if (escolha != 0) {
            print("Qual a quantidade? ")
            val quantidade = readLine()?.toInt() ?: 0
            valor = escolha * quantidade
            //concatenar valores na lista
            //listaDePaes.add((menuPaes[escolha] + precoPaes[escolha].toString()))
        } else {
            menuPrincipal()
        }
    } while (escolha != 0)

    return listaDePaes
}

fun menuDoces(): Int {
    var valor = 0
    val menuDoces = listOf("Doce francês", "Doce de milho")
    val precoDoces = listOf(10.00, 20.00)

    do {
        println("Escolha o item")
        for (i in menuDoces.indices) {
            println("${i + 1} - ${menuDoces[i]}......R$ ${precoDoces[i]}")
        }
        println("0.........Voltar")

        val escolha: Int = readLine()?.toInt() ?: 0

        if (escolha != 0) {
            print("Qual a quantidade? ")
            val quantidade = readLine()?.toInt() ?: 0
            valor = escolha * quantidade
        } else {
            menuPrincipal()
        }
    } while (escolha != 0)

    return valor
}