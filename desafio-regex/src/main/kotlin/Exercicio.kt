
fun main() {
    val padraoCPF = Regex("(^(\\d{3}.\\d{3}.\\d{3}-\\d{2}))")
    val padraoCNPJ = Regex("(^(\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}))")

    println("Informe um CPF para validar o formato (nnn.nnn.nnn-nn): ")
    val cpf: String = readln()
    val testeCPF: Boolean = cpf.matches(padraoCPF)

    if (testeCPF) {
        println("Você digitou no formato correto")
    } else {
        println("Você não digitou no formato correto")
    }

    println("Informe um CNPJ para validar o formato (mm.mmm.mmm/mmmm-mm)")
    val cnpj: String = readln()
    val testeCNPJ: Boolean = cnpj.matches(padraoCNPJ)

    if (testeCNPJ) {
        println("Você digitou no formato correto")
    } else {
        println("Você não digitou no formato correto")
    }

}