import kotlin.system.exitProcess

class EPadoca {
    private val itensComanda: MutableList<String> = mutableListOf()
    private var total: Double = 0.00
    private val menuPrincipal = ItemDoMenu(listOf("Pães", "Doces", "Salgados", "Adicionar Item"))
    private var paes =
        Produto(mutableListOf("Pão Francês", "Pão e Leite", "Pão de milho"), mutableListOf(1.0, 2.0, 3.0))
    private var doces =
        Produto(mutableListOf("Bolo", "Doce de milho", "Torta de Nozes"), mutableListOf(10.0, 12.0, 15.0))
    private var salgados = Produto(mutableListOf("Coxinha", "Misto quente", "Empadinha"), mutableListOf(4.0, 8.0, 3.0))

    fun menuPrincipal() {
        println("Selecione uma opcão")

        for (i in menuPrincipal.nome.indices) {
            println("${i + 1}.........${menuPrincipal.nome[i]}")
        }
        println("0.........Finalizar")

        var opcaoMenuPrincipal: Int = readlnOrNull()?.toInt() ?: 0
        when (opcaoMenuPrincipal) {
            1 -> menuPaes(paes)
            2 -> menuDoces(doces)
            3 -> menuSalgados(salgados)
            4 -> menuAdicionarItem(paes, doces, salgados)
            0 -> finalizarCompra()
            else -> "Opcão inválida"
        }
    }

    private fun menuPaes(paes: Produto) {
        exibirProdutos(paes.nome, paes.preco, 1, paes)
    }

    private fun menuDoces(doces: Produto) {
        exibirProdutos(doces.nome, doces.preco, 2, doces)
    }

    private fun menuSalgados(salgados: Produto) {
        exibirProdutos(salgados.nome, salgados.preco, 3, salgados)
    }

    private fun exibirProdutos(produtos: List<String>, precos: List<Double>, opcao: Int, lista: Produto) {
        try {
            do {
                println("Escolha o item")
                for (i in produtos.indices) {
                    println("${i + 1} - ${produtos[i]}......R$ ${precos[i]}")
                }
                println("0.........Voltar")

                val escolha: Int = readlnOrNull()?.toInt() ?: 0

                if (escolha != 0) {
                    print("Qual a quantidade? ")
                    val quantidade = readlnOrNull()?.toInt() ?: 0
                    selecionaQuantidadeDoProduto(produtos[escolha - 1], quantidade, precos[escolha - 1])
                } else {
                    menuPrincipal()
                }
            } while (escolha != 0)
        } catch (e: Exception) {
            println("Ocorreu um erro inesperado, por favor tente novamente.")
            when (opcao) {
                1 -> menuPaes(lista)
                2 -> menuDoces(lista)
                3 -> menuSalgados(lista)
            }
        }
    }

    private fun selecionaQuantidadeDoProduto(
        produto: String,
        quantidade: Int,
        precoUnitario: Double
    ): MutableList<String> {
        val subTotal = quantidade * precoUnitario
        val item = itemComanda(produto, quantidade, precoUnitario, subTotal)
        itensComanda.add(item)
        total += subTotal
        return itensComanda
    }

    private fun itemComanda(produto: String, quantidade: Int, valorUnitario: Double, subTotal: Double): String =
        "\n${itensComanda.size.inc()}$LINHA$produto$LINHA$quantidade${LINHA}R$$valorUnitario${LINHA}R$$subTotal\n"

    private fun menuAdicionarItem(paes: Produto, doces: Produto, salgados: Produto) {
        val tiposDeProdutos = ItemDoMenu(listOf("Pães", "Doces", "Salgados"))
        println("Selecione a qual lista deseja adicionar o produto")

        for (i in tiposDeProdutos.nome.indices) {
            println("${i + 1}.........${tiposDeProdutos.nome[i]}")
        }
        println("0.........Voltar")

        var opcaoAdicionarItem: Int = readlnOrNull()?.toInt() ?: 0
        when (opcaoAdicionarItem) {
            1 -> adicionarProduto(paes)
            2 -> adicionarProduto(doces)
            3 -> adicionarProduto(salgados)
            0 -> menuPrincipal()
            else -> "Opcão inválida"
        }
    }

    private fun adicionarProduto(lista: Produto) {
        if (lista == paes) {
            println("Informe o nome do produto")
            val produto = readlnOrNull() ?: ""
            println("Informe o valor: ")
            val valor = readlnOrNull()?.toDouble() ?: 0.0
            paes.nome.add(produto)
            paes.preco.add(valor)
        } else if (lista == doces) {
            println("Informe o nome do produto")
            val produto = readlnOrNull() ?: ""
            println("Informe o valor: ")
            val valor = readlnOrNull()?.toDouble() ?: 0.0
            doces.nome.add(produto)
            doces.preco.add(valor)
        } else if (lista == salgados) {
            println("Informe o nome do produto")
            val produto = readlnOrNull() ?: ""
            println("Informe o valor: ")
            val valor = readlnOrNull()?.toDouble() ?: 0.0
            salgados.nome.add(produto)
            salgados.preco.add(valor)
        }
        println("Item adicionado!")
        menuPrincipal()
    }

    private fun finalizarCompra() {
        if (itensComanda.isEmpty()) {
            print("Deseja mesmo cancelar? (S/N) ")
            if (readlnOrNull().toString() == "S") {
                exitProcess(0)
            } else {
                menuPrincipal()
            }
        } else {
            finalizacaoDaCompra()
        }
    }

    private fun finalizacaoDaCompra() {
        println("Se possui cupom de desconto, informe aqui, senão pressione enter: ")
        val cupomDeDesconto: String = readlnOrNull() ?: ""
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

    private fun impressaoDaComanda() {
        println("====================Comanda E-padoca=======================")
        println(DIVISOR)
        println("item.......Produto..........Qtd.......Valor...........Total")
        println(DIVISOR)
        itensComanda.forEach {
            print(it)
        }
        println("\n" + DIVISOR)
        println("Total =============================================>R$ $total")
        println("=====================VOLTE SEMPRE ^-^======================")
    }

    companion object {
        private const val LINHA = "........"
        private const val DIVISOR = "==========================================================="
    }
}

fun main() {
    println("Bem vindo a E-Padoca!")
    val ePadoca = EPadoca()
    ePadoca.menuPrincipal()
}