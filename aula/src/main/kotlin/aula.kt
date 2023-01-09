//data class TicketRequest(
//    val name: String,
//    val waitingTime: Int
//){
//    override fun toString(): String = "$name - Tempo de Espera: $waitingTime"
//}
//
//class Suporte{
//    private val queue: Queue<TicketRequest> = LinkedList()
//    private var currentTicket: TicketRequest? = null
//
//    fun addNewTicket(ticket: TicketRequest){
//        if(ticket.waitingTime<10)
//            queue.offer(ticket)
//    }
//
//    fun play(){
//        currentTicket = queue.poll()
//        println("Ticket: "+curren
//                currentTicket.toString())
//    }}
///*
* Criar uma estrutura para tratar um ticket de suporte, tratar apenas com waiting time
* menor que 10
* */

import java.util.LinkedList
import java.util.Queue

data class TicketRequest(
    val name: String,
    val waitingTime: Int
)

class SupportResolver(
    var queue: Queue<TicketRequest> = LinkedList()
){
    fun start() {
        var currentTicketRequest: TicketRequest?
        currentTicketRequest = queue.poll()
        while (currentTicketRequest != null){
            if (currentTicketRequest.waitingTime < 10){
                println("Resolvido")
            } else {
                println("Cancelado")
            }
            currentTicketRequest = queue.poll()
        }
    }

}
fun main() {
    var queue = LinkedList<TicketRequest>()
    queue.add(TicketRequest("A", 2))
    queue.add(TicketRequest("A", 12))
    queue.add(TicketRequest("A", 2))
    var resolver = SupportResolver(queue)
    resolver.start()
}