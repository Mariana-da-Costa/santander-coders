fun main(){
    println("--------------------")

    val pattern = Regex("^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}\$")
    val celular = "(12) 91234-5678"

    println("NumberTest: ${celular.matches(pattern)}")
}