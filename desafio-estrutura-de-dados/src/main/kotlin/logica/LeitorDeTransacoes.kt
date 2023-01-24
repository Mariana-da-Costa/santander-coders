package logica

import model.Banco
import model.Cliente
import model.Operacao
import model.Transacao
import java.time.LocalDateTime
import java.util.*

class LeitorDeTransacoes(private val banco: Banco) : Reader() {

    fun obterCsv(path: String): MutableSet<Transacao> {
        try {
            banco.transacoesBancarias = removeRepetidas(path)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return banco.transacoesBancarias
    }

    private fun obterListaDeTransacoes(path: String): List<Transacao> = readCsv(path).map {
        Transacao(
            cliente = Cliente(
                it[0].toInt(),
                it[1].toInt(),
                it[2],
                it[3],
                operacao = Operacao.valueOf(it[4].uppercase()),
                hora = LocalDateTime.parse(it[5]),
            ),
            operacao = Operacao.valueOf(it[4].uppercase()),
            hora = LocalDateTime.parse(it[5]),
            valor = it[6].toDouble()
        )
    }

    fun removeRepetidas(path: String) = obterListaDeTransacoes(path).toMutableSet()
    fun sort(path: String) = TreeSet(removeRepetidas(path))

    private fun obtemClientes(path: String) = sort(path).map {
        it.cliente
    }.toSet()

    fun obtemClientesESaldos(path: String): Set<Cliente> {
        val transacoesUnicas = removeRepetidas(path)
        val clientes = obtemClientes(path)

        clientes.forEach { cliente ->
            transacoesUnicas.filter {
                it.cliente == cliente
            }.forEach {
                if (it.operacao == Operacao.DEPOSITO) {
                    cliente.deposito(it.valor, it.hora)
                }
                if (it.operacao == Operacao.SAQUE) {
                    cliente.saque(it.valor, it.hora)
                }
            }
        }

        return clientes
    }
}