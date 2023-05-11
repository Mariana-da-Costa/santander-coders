package com.example.sorteio

fun main() {
    var random = 0
    val n1 = 1
    val n2 = 10
    random = (n1..n2).random()
    var inputed = 1
    validar(inputed, random)
}

fun validar(inputado: Int, sorteado: Int): String {
    var resultado: String = if (inputado < sorteado) {
        "O número sorteado é maior"
    } else if (inputado > sorteado) {
        "O número sorteado é menor"
    } else {
        "Você acertou!"
    }
    return resultado
}