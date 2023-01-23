import logica.LeitorDeTransacoes

fun main() {
    val leitor = LeitorDeTransacoes()
    val path = "utils/operacoes.csv"
    leitor.readCsv(path)
}