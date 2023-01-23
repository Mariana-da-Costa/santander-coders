package model

data class Banco(
    val name: String,
    var transacoesBancarias: MutableSet<Transacao> = mutableSetOf()
)
