import javax.naming.LimitExceededException
import kotlin.system.exitProcess

var listaDeProdutos = mutableListOf<Triple<Int, String, Int>>()
const val opcoesDoMenu = """
    1 - ADICIONAR ITEM
    2 - EDITAR ITEM
    3 - EXIBIR ITENS EM ESTOQUE
    4 - EXIBIR TODOS OS ITENS
    0 - FECHAR SISTEMA
"""

fun main() {
    println("*********** Bem vindo ao seu Gerenciador de Estoque =) ***********")
    menu()
}

fun menu() {
    println("\nDigite uma opção\n$opcoesDoMenu")
    val entradaOpcao = readLine()?.toInt() ?: 0
    try {
        when (entradaOpcao) {
            1 -> adicionarItem()
            2 -> editarItem()
            3 -> exibirProdutosEmEstoque()
            4 -> exibirListaDeProdutos()
            0 -> fecharPrograma()
        }
    } catch (e: LimitExceededException) {
        print(e.message)
    } catch (e: Exception) {
        println("Opção incorreta. Digite uma das seguintes opções: $opcoesDoMenu")
        menu()
    }
}

fun adicionarItem() {
    if (listaDeProdutos.size == 999) {
        throw LimitExceededException("LimiteEstoqueMaxException")
    } else {
        val qtd: Int
        val index = listaDeProdutos.size.inc()
        print("Informe o nome do produto: ")
        try {
            val produto = readLine().toString()
            print("Informe a quantidade: ")
            qtd = readLine()?.toInt() ?: 0
            val item = item(index, produto, qtd)
            listaDeProdutos.add(Triple(index, item, qtd))
            menu()
        } catch (e: Exception) {
            println("!!! A quantidade precisa ser um número maior ou igual a zero !!! \n")
            adicionarItem()
        }
    }
}

fun editarItem() {
    if (listaDeProdutos.isNotEmpty()) {
        println("Qual desses itens deseja editar? ")
        exibirListaDeProdutos()
        print("\n Digite o ID do produto que deseja editar: ")
        try {
            val index = readLine()?.toInt() ?: -1
            println("\nVocê deseja alterar o item ${listaDeProdutos[index - 1].first}")
            print("Informe o novo nome do produto: ")
            val novoNome = readLine().toString()
            print("Informe a nova quantidade: ")
            val novaQuantidade = readLine()?.toInt() ?: 0
            val itemEditado = item(listaDeProdutos[index].first, novoNome, novaQuantidade)
            listaDeProdutos[0] = Triple(index, itemEditado, novaQuantidade)
            menu()
            exibirListaDeProdutos()
        } catch (e: IndexOutOfBoundsException) {
            println("Você digitou incorretamente")
            editarItem()
        } catch (e: NumberFormatException) {
            println("Você digitou uma letra, digite o número do ID")
            editarItem()
        }
    } else {
        println("A lista está vazia =(")
        menu()
    }
}

fun exibirProdutosEmEstoque() {
    if (listaDeProdutos.isNotEmpty()) {
        val produtosEmEstoque = listaDeProdutos.filter { it.third > 0 }
        println("ID | Peça | Quantidade")
        produtosEmEstoque.forEach {
            println(it.second)
        }
    } else {
        println("A lista está vazia =(")
    }
    menu()
}

fun exibirListaDeProdutos() {
    if (listaDeProdutos.isNotEmpty()) {
        println("ID | Peça | Quantidade")
        listaDeProdutos.forEach {
            println(it.second)
        }
    } else {
        println("A lista está vazia =(")
    }
    menu()
}

fun fecharPrograma() {
    println("********** Volte sempre! =) **********")
    exitProcess(0)
}

fun item(id: Int, peca: String, qtd: Int): String = "#$id | $peca | $qtd"