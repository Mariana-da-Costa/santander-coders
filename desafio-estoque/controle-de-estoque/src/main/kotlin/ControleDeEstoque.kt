import java.io.File
import javax.naming.LimitExceededException
import kotlin.system.exitProcess

var listaDeProdutos = mutableListOf<Triple<Int, String, Int>>()
const val opcoesDoMenu = """
    1 - ADICIONAR ITEM
    2 - EDITAR ITEM
    3 - EXIBIR ITENS EM ESTOQUE
    4 - EXIBIR TODOS OS ITENS
    5 - GRAVAR ITENS NO TXT
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
            4 -> exibirListaDeProdutos(true)
            5 -> gravandoLista(true)
            0 -> fecharPrograma()
        }
    } catch (e: Exception) {
        println("Opção incorreta. Digite uma das seguintes opções: $opcoesDoMenu")
        menu()
    }
}

fun adicionarItem() {
    val qtd: Int
    val index = listaDeProdutos.size.inc()
    print("Informe o nome do produto: ")
    try {
        val produto = readLine().toString()
        print("Informe a quantidade: ")
        qtd = readLine()?.toInt() ?: 0
        if (qtd > 999) {
            throw LimitExceededException("LimiteEstoqueMaxException")
        } else {
            val item = item(index, produto, qtd)
            listaDeProdutos.add(Triple(index, item, qtd))
            menu()
        }
    } catch (e: LimitExceededException) {
        println(e.message)
        println("!!! A quantidade precisa ser um número entre 0 e 999 !!! \n")
        adicionarItem()
    } catch (e: Exception) {
        println("!!! A quantidade precisa ser um número entre 0 e 999 !!! \n")
        adicionarItem()
    }
}

fun editarItem() {
    if (listaDeProdutos.isNotEmpty()) {
        println("Qual desses itens deseja editar? ")
        exibirListaDeProdutos(false)
        print("\n Digite o ID do produto que deseja editar: ")
        try {
            val index = readLine()?.toInt() ?: -1
            println("\nVocê deseja alterar o item ${listaDeProdutos[index - 1].first}")
            print("Informe o novo nome do produto: ")
            val novoNome = readLine().toString()
            print("Informe a nova quantidade: ")
            val novaQuantidade = readLine()?.toInt() ?: 0
            if (novaQuantidade > 999) {
                throw LimitExceededException("LimiteEstoqueMaxException")
            } else {
                val itemEditado = item(listaDeProdutos[index - 1].first, novoNome, novaQuantidade)
                listaDeProdutos[index - 1] = Triple(index, itemEditado, novaQuantidade)
                println("Produto alterado com sucesso: ${listaDeProdutos[index - 1].second}")
            }
        } catch (e: LimitExceededException) {
            println(e.message)
            println("!!! A quantidade precisa ser um número entre 0 e 999 !!! \n")
            editarItem()
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
    menu()
}

fun exibirProdutosEmEstoque() {
    if (listaDeProdutos.isNotEmpty()) {
        val produtosEmEstoque = listaDeProdutos.filter { it.third > 0 }
        println("ID | Peça | Quantidade")
        produtosEmEstoque.forEach { println(it.second) }
    } else {
        println("A lista está vazia =(")
    }
    menu()
}

fun exibirListaDeProdutos(exibirMenu: Boolean) {
    if (listaDeProdutos.isNotEmpty()) {
        println("ID | Peça | Quantidade")
        listaDeProdutos.forEach { println(it.second) }
    } else {
        println("A lista está vazia =(")
    }
    if (exibirMenu) menu()
}

fun fecharPrograma() {
    println("*************************** Volte sempre! =) ***************************")
    gravandoLista(false)
    exitProcess(0)
}

fun gravandoLista(exibirMenu: Boolean) {
    val arquivo = File("listaDeProdutos.txt")
    arquivo.writeText("ID  |   Peça   | Quantidade\n\n")
    listaDeProdutos.forEach { arquivo.appendText("${it.second}\n") }
    println("********** Sua lista foi salva no arquivo listaDeProdutos.txt **********")
    if (exibirMenu) menu()
}

fun item(id: Int, peca: String, qtd: Int): String = "#$id | $peca | $qtd"