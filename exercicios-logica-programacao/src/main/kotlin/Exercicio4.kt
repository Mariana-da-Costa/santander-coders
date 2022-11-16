fun main(){

    println("Informe o número de eleitores:")
    val qtdVotos: Int = readln().toInt()
    var candidatoA = 0
    var candidatoB = 0
    var candidatoC = 0

    for (i in 1..qtdVotos) {
        println("Vote A, para o candidato A; vote B, para o Candidato B; e vote C, para o Candidato C!")
        when (readln().uppercase()) {

            "A" -> candidatoA++
            "B" -> candidatoB++
            "C" -> candidatoC++

            else -> println("Voto inválido!")
        }
    }
    val votosValidos: Float = (candidatoA + candidatoB + candidatoC).toFloat()
    val porcentagemA: Float = (candidatoA.toFloat() / votosValidos) * 100
    val porcentagemB: Float = (candidatoB.toFloat() / votosValidos) * 100
    val porcentagemC: Float = (candidatoC.toFloat() / votosValidos) * 100

    println("Candidato A - $candidatoA votos ($porcentagemA %)")
    println("Candidato B - $candidatoB votos ($porcentagemB %)")
    println("Candidato C - $candidatoC votos ($porcentagemC %)")
}