import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val padraoCPF = Regex("(^(\\d{3}.\\d{3}.\\d{3}-\\d{2}))")
    val padraoCNPJ = Regex("(^(\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}))")

    println("Informe um CPF para validar o formato (nnn.nnn.nnn-nn): ")
    val cpf: String = scanner.nextLine() //nome da variável, ":" tipo da variável, "=" declaracao da variável
    val testeCPF: Boolean = cpf.matches(padraoCPF)

    if (testeCPF) {
        println("Você digitou no formato correto")
    } else {
        println("Você não digitou no formato correto")
    }

    println("Informe um CNPJ para validar o formato (mm.mmm.mmm/mmm-mm")
    val cnpj: String = scanner.nextLine()
    val testeCNPJ: Boolean = cnpj.matches(padraoCNPJ)

    if (testeCNPJ) {
        println("Você digitou no formato correto")
    } else {
        println("Você não digitou no formato correto")
    }

}