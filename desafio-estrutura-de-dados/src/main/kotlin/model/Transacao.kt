package model

import java.time.LocalDateTime

data class Transacao(
    var cliente: Cliente,
    var operacao: Operacao,
    var hora: LocalDateTime,
    var valor: Double
) : Comparable<Transacao> {

    override fun toString(): String {
        return "Transacao(agencia=${cliente.agencia}," +
                " conta=${cliente.conta}," +
                " banco='${cliente.banco}'," +
                " titular='${cliente.titular}'," +
                " operacao='$operacao'," +
                " dataHora='$hora'," +
                " valor=$valor)"
    }

    override fun compareTo(other: Transacao): Int {
        if (cliente.conta > other.cliente.conta && hora > other.hora) return 1
        if (cliente.conta < other.cliente.conta && hora < other.hora) return -1
        return 1
    }

}