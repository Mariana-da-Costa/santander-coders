package carteira

internal abstract class GeradorDeSenhas<T>(val tamanho: Int) {
    abstract fun gerar(): T
}

internal class GeradorDeSenha(qtd: Int) : GeradorDeSenhas<String>(qtd) {
    override fun gerar(): String {
        val senhaDefault = "4321"
        var senhaCriada = ""
        for (i in 1..tamanho) {
            senhaCriada += senhaDefault.random()
        }
        return senhaCriada
    }
}