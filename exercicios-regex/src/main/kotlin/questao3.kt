fun main(){
    println("--------------------")

    val pattern = Regex("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+).(\\.[a-z]{2,3})\$|^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+\$")
    val emailcom = "marianacosta.dev@gmail.com"
    val valorcombr = "marianacosta.dev@gmail.com.br"

    println("NumberTest: ${emailcom.matches(pattern)}")
    println("NumberTest: ${valorcombr.matches(pattern)}")
}