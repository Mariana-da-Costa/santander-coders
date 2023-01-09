import kotlin.random.Random

//Enunciado
//Temos um serviço de mensageria que ao receber uma nova mensagem a coloca numa fila e tenta enviar a mensagem ao
//destinatário, acontece que as vezes temos problemas ao tentar entregar a mensagem, por isso é necessário
//que tentemos entregar a mesma mensagemm até 3 vezes.
//
//Implementar uma fila dentro do MessageBroker onde seja possível retentar o envio das mensagens por
//pelo menos 3 vezes, caso a terceira tentativa seja uma falha, descartar a mensagem.

data class Message(
    val text: String,
    val recipient: String
)

class MessageBroker {
    private val messageSender = Random(1000)

    fun processFirstMessage() {
        /*
        * Implementar maneira de processar a fila e de retentar o envio de uma mensagem que deu erro
        */
        //send(message)
    }

    /*
    * Retorna true ao conseguir enviar uma mensagem com sucesso e false se não conseguir
    */
    private fun send(message: Message): Boolean {
        return messageSender.nextBoolean()
    }
}