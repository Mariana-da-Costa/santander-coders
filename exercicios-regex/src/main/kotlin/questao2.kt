fun main(){
    println("--------------------")

    val pattern = Regex("R\\\$ ?\\d{1,3},\\d{2}")
    val valor = "R$ 7,50"

    println("NumberTest: ${valor.matches(pattern)}")
}