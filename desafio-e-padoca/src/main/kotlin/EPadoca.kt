import kotlin.system.exitProcess

private const val LINHA = "........"
private const val DIVISOR = "==========================================================="
val itensComanda: MutableList<String> = mutableListOf()
var total: Double = 0.00

fun main() {
    println("Bem vindo a E-Padoca!")
    menuPrincipal()
}

fun menuPrincipal() {
    val menuPrincipal = listOf("Pães", "Doces")

    println("Selecione uma opcão")

    for (i in menuPrincipal.indices) {
        println("${i + 1}.........${menuPrincipal[i]}")
    }
    println("0.........Finalizar")

    var opcaoMenuPrincipal: Int = readLine()?.toInt() ?: 0

    when (opcaoMenuPrincipal) {
        1 -> menuPaes()
        2 -> menuDoces()
        0 -> finalizarCompra()
        else -> "Opcão inválida"
    }
}

fun menuPaes() {
    val listaPaes = listOf("Pão francês", "Pão de milho")
    val precoPaes = listOf(1.0, 2.0)

    do {
        println("Escolha o item")
        for (i in listaPaes.indices) {
            println("${i + 1} - ${listaPaes[i]}......R$ ${precoPaes[i]}")
        }
        println("0.........Voltar")

        val escolha: Int = readLine()?.toInt() ?: 0

        if (escolha != 0) {
            print("Qual a quantidade? ")
            val quantidade = readLine()?.toInt() ?: 0
            selecionaQuantidadeDoProduto(listaPaes[escolha - 1], quantidade, precoPaes[escolha - 1])
        } else {
            menuPrincipal()
        }
    } while (escolha != 0)

}

fun menuDoces(){
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
            selecionaQuantidadeDoProduto(menuDoces[escolha - 1], quantidade, precoDoces[escolha - 1])
        } else {
            menuPrincipal()
        }
    } while (escolha != 0)
}

fun selecionaQuantidadeDoProduto(produto: String, quantidade: Int, precoUnitario: Double): MutableList<String> {
    val subTotal = quantidade * precoUnitario
    val item = itemComanda(produto, quantidade, precoUnitario, subTotal)
    itensComanda.add(item)
    total += subTotal
    return itensComanda
}

fun itemComanda(
    produto: String,
    quantidade: Int,
    valorUnitario: Double,
    subTotal: Double,
): String = "\n${itensComanda.size.inc()}$LINHA$produto$LINHA$quantidade${LINHA}R$$valorUnitario${LINHA}R$$subTotal"

fun finalizarCompra() {
    if (itensComanda.isEmpty()) {
        print("Deseja mesmo cancelar? (S/N) ")
        if (readLine().toString() == "S") {
            exitProcess(0)
        } else {
            menuPrincipal()
        }
    } else {
        finalizacaoDaCompra()
    }
}

fun finalizacaoDaCompra() {
    println("Se possui cupom de desconto, informe aqui: ")
    val cupomDeDesconto: String = readLine() ?: ""
    if (cupomDeDesconto.isNotEmpty()) {
        when (cupomDeDesconto) {
            "5PADOCA" -> total -= (total * 0.05)
            "10PADOCA" -> total -= (total * 0.1)
            "5OFF" -> total -= 5
            else -> "Opcão inválida"
        }
    }
    impressaoDaComanda()
}

fun impressaoDaComanda() {
    println("====================Comanda E-padoca=======================")
    println(DIVISOR)
    println("item.......Produto..........Qtd.......Valor...........Total")
    println(DIVISOR)
    print(itensComanda)
    println("\n" + DIVISOR)
    println("Total =============================================>R$ $total")
    println("=====================VOLTE SEMPRE ^-^======================")
}