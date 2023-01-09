import java.util.*
import kotlin.random.Random

data class Message(val text: String, val recipient: String) {
    var sendAttempt: Int = 0
}

class MessageBroker {
    val queue: Queue<Message> = LinkedList()
    private val messageSender = Random(1000)

    fun processFirstMessage() {
        val lastMessageInQueue = queue.peek()
        val send = send()
        if (send && lastMessageInQueue.sendAttempt < 3) {
            println(
                """
                Mensagem enviada.
                Text: ${lastMessageInQueue.text}
                Recipient: ${lastMessageInQueue.recipient}
                Attempt: ${lastMessageInQueue.sendAttempt}
                 """.trimIndent()
            )
            queue.poll()
        } else if (lastMessageInQueue.sendAttempt == 3) {
            println(
                """
                Tentativa de envio abortada.
                Text: ${lastMessageInQueue.text}
                Recipient: ${lastMessageInQueue.recipient}
                 """.trimIndent()
            )
            queue.poll()
        } else {
            queue.poll()
            lastMessageInQueue.sendAttempt++
            queue.offer(lastMessageInQueue)
        }
    }

    private fun send(): Boolean {
        return messageSender.nextBoolean()
    }
}

fun main() {
    val menssagem1 = Message("Olá, sou aluna da ADA", "marianacosta.dev@gmail.com")
    val menssagem2 = Message("O que está estudando?", "123456@rsrsrs.com")
    val menssagem3 = Message("Kotlin! =)", "marianacosta.dev@gmail.com")

    val messageBroker = MessageBroker()
    messageBroker.queue.offer(menssagem1)
    messageBroker.queue.offer(menssagem2)
    messageBroker.queue.offer(menssagem3)

    with(messageBroker) {
        while (this.queue.isNotEmpty()) {
            this.processFirstMessage()
        }
    }
}