package cliente

import carteira.CarteiraDigital
import carteira.CarteiraFisica
import carteira.Plano

abstract class Cliente(val nome: String, val sobrenome: String, val cpf: String, var senha: String, var plano: Plano)

internal class ClienteNormal(nome: String, sobrenome: String, cpf: String, senha: String, plano: Plano) :
    Cliente(nome, sobrenome, cpf, senha, plano) {

    val carteiraFisica = CarteiraFisica(this.senha, Plano.NORMAL, 0.0, "")

}

internal class ClienteDigital(nome: String, sobrenome: String, cpf: String, senha: String, plano: Plano) :
    Cliente(nome, sobrenome, cpf, senha, plano) {

    val carteiraDigital = CarteiraDigital(this.senha, Plano.DIGITAL, 0.0, "")

}

internal class ClientePremium(nome: String, sobrenome: String, cpf: String, senha: String, plano: Plano) :
    Cliente(nome, sobrenome, cpf, senha, plano) {

    val carteiraFisica = CarteiraFisica(this.senha, Plano.PREMIUM, 0.0, "")
    val carteiraDigital = CarteiraDigital(this.senha, Plano.PREMIUM, 0.0, "")

}