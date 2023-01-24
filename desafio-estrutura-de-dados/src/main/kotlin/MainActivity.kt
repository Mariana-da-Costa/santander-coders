import logica.LeitorDeTransacoes
import logica.Writer
import model.Banco

fun main() {
    val banco = Banco("SANTANDER")
    val bankReader = LeitorDeTransacoes(banco)
    val path = "utils/operacoes.csv"
    val writerCsv = Writer()

    bankReader.obterCsv(path)
    bankReader.sort(path).forEach {
        println(it)
    }

    bankReader.obtemClientesESaldos(path).forEach {
        println(it)
    }

    writerCsv.apply {
        val pathDeals = "utils/operacoesProcessadas.csv"
        writeCsv(pathDeals, bankReader.obtemClientesESaldos(path))
    }
}