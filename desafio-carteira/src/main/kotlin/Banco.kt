import carteira.GeradorDeSenha
import carteira.Plano
import cliente.ClienteNormal

fun main() {
    val clienteTeste = ClienteNormal(
        "Mariana", "da Costa", "123",
        GeradorDeSenha(4).gerar(),
        Plano.PREMIUM
    )

    println(clienteTeste.carteiraFisica.senha)
    println(clienteTeste.carteiraFisica.verSaldo())
    println(clienteTeste.carteiraFisica.depositar(2750.0))
    println(clienteTeste.carteiraFisica.sacar(10.0))
    println(clienteTeste.carteiraFisica.pagarBoleto(242.0))
    println(clienteTeste.carteiraFisica.verSaldo())
    println(clienteTeste.carteiraFisica.pagarBoleto(2.0))
    println(clienteTeste.carteiraFisica.verSaldo())
    println(clienteTeste.carteiraFisica.sacar(1010.0))
    println(clienteTeste.carteiraFisica.verSaldo())
}