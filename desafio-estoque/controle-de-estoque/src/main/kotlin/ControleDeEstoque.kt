import java.io.File
import javax.naming.LimitExceededException
import kotlin.system.exitProcess

class ControleDeEstoque {
    private var listaDeProdutos = mutableListOf<Triple<Int, String, Int>>()
    private val opcoesDoMenu = """
    1 - ADICIONAR ITEM
    2 - EDITAR ITEM
    3 - EXIBIR ITENS EM ESTOQUE
    4 - EXIBIR TODOS OS ITENS
    5 - GRAVAR ITENS NO TXT
    0 - FECHAR SISTEMA
"""

    fun menu() {
        println("\nDigite uma opção\n$opcoesDoMenu")
        val entradaOpcao = readlnOrNull()?.toInt() ?: 0
        try {
            when (entradaOpcao) {
                1 -> adicionarItem()
                2 -> editarItem()
                3 -> exibirProdutosEmEstoque()
                4 -> exibirListaDeProdutos(true)
                5 -> gravandoLista(true)
                0 -> fecharPrograma()
            }
        } catch (e: Exception) {
            println("Opção incorreta. Digite uma das seguintes opções: $opcoesDoMenu")
            menu()
        }
    }

    private fun adicionarItem() {
        val qtd: Int
        val index = listaDeProdutos.size.inc()
        print("Informe o nome do produto: ")
        try {
            val produto = readlnOrNull().toString()
            print("Informe a quantidade: ")
            qtd = readlnOrNull()?.toInt() ?: 0
            if (qtd > 999) {
                throw LimitExceededException("LimiteEstoqueMaxException")
            } else {
                val item = item(index, produto, qtd)
                listaDeProdutos.add(Triple(index, item, qtd))
                menu()
            }
        } catch (e: LimitExceededException) {
            println(e.message)
            println(INTERVALO_NUMERICO)
            adicionarItem()
        } catch (e: Exception) {
            println(INTERVALO_NUMERICO)
            adicionarItem()
        }
    }

    private fun editarItem() {
        if (listaDeProdutos.isNotEmpty()) {
            println("Qual desses itens deseja editar? ")
            exibirListaDeProdutos(false)
            print("\n Digite o ID do produto que deseja editar: ")
            try {
                val index = readlnOrNull()?.toInt() ?: -1
                println("\nVocê deseja alterar o item ${listaDeProdutos[index - 1].first}")
                print("Informe o novo nome do produto: ")
                val novoNome = readlnOrNull().toString()
                print("Informe a nova quantidade: ")
                val novaQuantidade = readlnOrNull()?.toInt() ?: 0
                if (novaQuantidade > 999) {
                    throw LimitExceededException("LimiteEstoqueMaxException")
                } else {
                    val itemEditado = item(listaDeProdutos[index - 1].first, novoNome, novaQuantidade)
                    listaDeProdutos[index - 1] = Triple(index, itemEditado, novaQuantidade)
                    println("Produto alterado com sucesso: ${listaDeProdutos[index - 1].second}")
                }
            } catch (e: LimitExceededException) {
                println(e.message)
                println(INTERVALO_NUMERICO)
                editarItem()
            } catch (e: IndexOutOfBoundsException) {
                println("Você digitou incorretamente")
                editarItem()
            } catch (e: NumberFormatException) {
                println("Você digitou uma letra, digite o número do ID")
                editarItem()
            }
        } else {
            println(LISTA_VAZIA)
            menu()
        }
        menu()
    }

    private fun exibirProdutosEmEstoque() {
        if (listaDeProdutos.isNotEmpty()) {
            val produtosEmEstoque = listaDeProdutos.filter { it.third > 0 }
            println(CABECALHO)
            produtosEmEstoque.forEach { println(it.second) }
        } else {
            println(LISTA_VAZIA)
        }
        menu()
    }

    private fun exibirListaDeProdutos(exibirMenu: Boolean) {
        if (listaDeProdutos.isNotEmpty()) {
            println(CABECALHO)
            listaDeProdutos.forEach { println(it.second) }
        } else {
            println(LISTA_VAZIA)
        }
        if (exibirMenu) menu()
    }

    private fun fecharPrograma() {
        println(VOLTE_SEMPRE)
        gravandoLista(false)
        exitProcess(0)
    }

    private fun gravandoLista(exibirMenu: Boolean) {
        val arquivo = File("listaDeProdutos.txt")
        arquivo.writeText(LISTA_VAZIA + "\n\n")
        listaDeProdutos.forEach { arquivo.appendText("${it.second}\n") }
        println(LISTA_SALVA)
        if (exibirMenu) menu()
    }

    private fun item(id: Int, peca: String, qtd: Int): String = "#$id | $peca | $qtd"

    companion object {
        private const val INTERVALO_NUMERICO = "!!! A quantidade precisa ser um número entre 0 e 999 !!! \n"
        private const val CABECALHO = "ID | Peça | Quantidade"
        private const val LISTA_VAZIA = "A lista está vazia =("
        private const val VOLTE_SEMPRE = "*************************** Volte sempre! =) ***************************"
        private const val LISTA_SALVA = "********** Sua lista foi salva no arquivo listaDeProdutos.txt **********"
    }
}

fun main() {
    println("*********** Bem vindo ao seu Gerenciador de Estoque =) ***********")
    val controleDeEstoque = ControleDeEstoque()
    controleDeEstoque.menu()
}