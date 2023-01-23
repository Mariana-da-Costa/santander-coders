package model

import java.time.LocalDateTime

data class Cliente(
    val agencia: Int,
    val conta: Int,
    val banco: String,
    val titular: String,
    var operacao: Operacao,
    var hora: LocalDateTime,
    var saldo: Double = 0.0
) {
    fun saque(valor: Double, hora: LocalDateTime = LocalDateTime.now()): Transacao? = if (valor > 0) {
        saldo -= valor
        Transacao(this, Operacao.SAQUE, hora, valor)
    } else {
        null
    }

    fun deposito(valor: Double, hora: LocalDateTime = LocalDateTime.now()): Transacao? = if (valor > 0) {
        saldo += valor
        Transacao(this, Operacao.DEPOSITO, hora, valor)
    } else {
        null
    }

}