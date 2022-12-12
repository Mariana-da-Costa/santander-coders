abstract class AquarioAbstrato {
    var criterioDeLimpeza = 3
    var tamanhoDoAquario = 5
    abstract fun adicionarPeixe(peixe: Peixe)
    abstract fun alimentarPeixe()
    abstract fun alterarCriterioDeLimpeza(valor: Int)
    abstract fun verificarAquario(listaDePeixes: MutableList<Peixe>, proibidoAdicionarPeixe: Boolean)
    abstract fun limparAquario()
    abstract fun upgradeDeAquario()
    abstract fun constroiAquario(tamanho: Int)
}

class Aquario : AquarioAbstrato() {
    val listaDePeixe = mutableListOf<Peixe>()
    var proibidoAdicionarPeixe = false

    override fun adicionarPeixe(peixe: Peixe) {
        if (proibidoAdicionarPeixe) {
            println("Aquário sujo! É necessário limpar primeiro antes de adicionar um novo peixe!")
        } else if (listaDePeixe.size == tamanhoDoAquario) {
            println("O aquário atingiu a capacidade! Faça upgrade!")
        } else {
            listaDePeixe.add(peixe)
            verificarAquario(listaDePeixe, proibidoAdicionarPeixe)
            println("Peixe adicionado com sucesso!")
        }
    }

    override fun alimentarPeixe() {
        val randon = (0..listaDePeixe.size).random()
        val resultado: String

        when (randon) {
            0 -> resultado = "Falha"
            listaDePeixe.size -> resultado = "Sucesso"
            else -> resultado = "Parcial, $randon peixes se alimentaram"
        }

        return println(resultado)
    }

    override fun alterarCriterioDeLimpeza(valor: Int) {
        criterioDeLimpeza = valor
        verificarAquario(listaDePeixe, proibidoAdicionarPeixe)
        println("Critério de limpeza alterado para $valor")
    }

    override fun verificarAquario(listaDePeixes: MutableList<Peixe>, proibidoAdicionarPeixe: Boolean) {
        if (listaDePeixes.size % criterioDeLimpeza == 0) {
            this.proibidoAdicionarPeixe = true
        } else if (listaDePeixes.size % criterioDeLimpeza != 0) {
            this.proibidoAdicionarPeixe = false
        }
    }

    override fun limparAquario() {
        this.proibidoAdicionarPeixe = false
        println("O aquário foi limpo com sucesso!")
    }

    override fun upgradeDeAquario() {
        try {
            println("Para qual tamanho deseja aumentar seu aquário? Digite o valor")
            println(
                """
    5 = PEQUENO = CAPACIDADE 5 PEIXES
    20 = MÉDIO = CAPACIDADE 20 PEIXES
    100 = GRANDE = CAPACIDADE 100 PEIXES
"""
            )
            var tamanho = tamanhoDoAquario
            tamanho = readln().toInt()
            if (tamanho == 5 || tamanho == 20 || tamanho == 100) {
                constroiAquario(tamanho)
            } else {
                print("Você digitou $tamanho, necessário digitar 5, 20 ou 100")
            }
        } catch (e: Exception) {
            print("Você digitou uma letra, necessário digitar 5, 20 ou 100, repita a operação")
            upgradeDeAquario()
        }
    }

    override fun constroiAquario(tamanho: Int) {
        tamanhoDoAquario = tamanho
        println("O aquário foi atualizado com sucesso! Novo tamanho $tamanhoDoAquario")
    }
}

fun main() {
    val aquario = Aquario()
    println("*** INÍCIO DOS TESTES ***\n\n")
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.alimentarPeixe()
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.alterarCriterioDeLimpeza(6)
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.limparAquario()
    aquario.adicionarPeixe(Peixe("Peixinho", "Azul", "Pequeno"))
    aquario.alimentarPeixe()
    aquario.alimentarPeixe()
    aquario.alimentarPeixe()
    aquario.alimentarPeixe()
    aquario.alimentarPeixe()
    aquario.alimentarPeixe()
    aquario.upgradeDeAquario()
    println("\n\n*** FIM DOS TESTES ***")
}