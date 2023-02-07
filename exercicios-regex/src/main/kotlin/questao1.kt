fun main(){
    println("--------------------")

    val pattern = Regex("(^(\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2})|(\\d{14})\$)|(^(\\d{3}.\\d{3}.\\d{3}-\\d{2})|(\\d{11})\$)")
    val cpf = "12345678910"
    val cnpj = "12345678912345"

    println("NumberTest: ${cpf.matches(pattern)}")
    println("NumberTest: ${cnpj.matches(pattern)}")
}