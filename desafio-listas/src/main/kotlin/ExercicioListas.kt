
//Enunciado
//Dado a estrutura de lista ligada que construímos em sala, adicionar:
//Método de busca por um valor específico
//Otimização para termos a adição de um nó ao final de O(1), ou seja,
//será necessário manter uma referência para o último elemento da lista

data class Node<T>(
    var value: T,
    var next: Node<T>?
)

data class LinkedList2<T>(
    var head: Node<T>? = null
) {
    fun add(value: T) {
        val newNode = Node(value = value, null)

        if (head == null) {
            head = newNode
            return
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
    }

    fun remove(value: T): Boolean {
        if (head?.value == value) {
            head = head?.next
            return true
        }
        var current = head?.next
        var previous = head

        while(current != null && current.value != value) {
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

        while(current != null) {
            println(current.value)
            current = current.next
        }
    }

    fun isEmpty(): Boolean {
        return head == null
    }
}