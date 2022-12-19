package carteira

internal abstract class Carteira(val senha: String, val tipo: Plano, var saldo: Double, val extrato: String) {
    abstract fun pagarBoleto(valor: Double): String
}

internal open class CarteiraFisica(senha: String, tipo: Plano, saldo: Double, extrato: String) :
    Carteira(senha, tipo, saldo, extrato) {

    override fun pagarBoleto(valor: Double): String {
        this.saldo -= valor
        return "Boleto pago de $valor \nSaldo: R$ ${this.saldo}"
    }

    fun depositar(valor: Double): String {
        this.saldo += valor
        return "Depósito de R$ $valor"
    }

    fun sacar(valor: Double): String {
        this.saldo -= valor
        return "Saque de R$ $valor"
    }

    fun verSaldo(): String {
        return "Saldo de R$ ${this.saldo}"
    }
}

internal open class CarteiraDigital(senha: String, tipo: Plano, saldo: Double, extrato: String) :
    Carteira(senha, tipo, saldo, extrato) {

    fun transferenciaPix(valor: Double) {
        println("Transferência de R$ $valor")
    }

    override fun pagarBoleto(valor: Double): String {
        this.saldo -= valor
        return "Boleto pago de $valor \nSaldo: R$ ${this.saldo}"
    }

    fun investir(valor: Double) {
        this.saldo += valor
        println("Investimento de R$ $valor")
    }

    fun guardar(valor: Double) {
        this.saldo += valor
        println("Guardado R$ $valor")
    }
}