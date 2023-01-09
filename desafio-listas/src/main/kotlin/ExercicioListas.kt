data class Node<T>(var value: T, var next: Node<T>?)

data class LinkedList<T>(var head: Node<T>? = null) {
    private var ultimo: Node<T>? = null

    fun add(value: T) {
        val newNode = Node(value = value, null)

        if (head == null) {
            head = newNode
            ultimo = newNode
            return
        } else {
            val current = ultimo
            current?.next = newNode
            ultimo = newNode
        }
    }

    fun obtemNode(searchedValue: T): Node<T>? {
        var current = head
        if (current == null) {
            println("Lista vazia")
            return null
        }
        do {
            if (current?.value == searchedValue) {
                return current
            }
            current = current?.next
        } while (current != null)

        println("O item não existe na lista")
        return null
    }

    fun obtemIndice(searchedValue: T): Int? {
        var current = head
        var currentIndex = 0
        if (current == null) {
            println("Lista vazia")
            return null
        }
        do {
            if (current?.value == searchedValue) {
                return currentIndex
            }
            current = current?.next
            currentIndex++
        } while (current != null)

        println("O item não existe na lista")
        return null
    }

    fun remove(value: T): Boolean {
        if (head?.value == value) {
            head = head?.next
            return true
        }
        var current = head?.next
        var previous = head

        while (current != null && current.value != value) {
            previous = current
            current = current.next
        }

        if (current != null) {
            previous?.next = current.next
            return true
        }

        return false
    }

    fun show() {
        var current = head
        if (current == null) {
            println("Lista vazia")
            return
        }

        while (current != null) {
            println(current.value)
            current = current.next
        }
    }

    fun isEmpty(): Boolean {
        return head == null
    }
}

fun main() {
    val lista = LinkedList<Int>()
    lista.add(12)
    lista.add(28)
    lista.add(21)
    lista.add(33)
    lista.add(34)
    lista.add(35)
    lista.show()
    lista.remove(12)
    lista.show()

    val indicePesquisado = lista.obtemIndice(3)
    val resultadoNode = lista.obtemNode(28)

    println(resultadoNode)
    println(indicePesquisado)
}