import java.lang.StringBuilder
import java.util.*

class ShuntYard {
    private val outputQueue: Queue<String> = LinkedList()
    private val operatorStack: Stack<String> = Stack()

    fun readToken() {
        var token: String? = null
        while (token.isNullOrBlank()) {
            token = readlnOrNull()
            if (token.isNullOrBlank()) {
                continue
            } else {
                convertNotation(token)
            }
        }
    }

    private fun convertNotation(token: String) {
        for (i in token.indices) {
            val containsNum = token.substring(i, i + 1).contains(regex = Regex("^[0-9]$"))
            val containsOperator = token.substring(i, i + 1).contains(regex = Regex("[-+x/^]"))
            val containsLeftParenthesis = token.substring(i, i + 1).contains(regex = Regex("^[(]$"))
            val containsRightParenthesis = token.substring(i, i + 1).contains(regex = Regex("^[)]$"))
            val isOperatorO1: Boolean = if (operatorStack.isNotEmpty()) {
                ((operatorStack.peek() != "(") &&
                        (isPrecedence(token.substring(i, i + 1), operatorStack.peek())))
            } else {
                false
            }

            when {
                containsNum -> {
                    outputQueue.offer(token.substring(i, i + 1))
                }
                containsRightParenthesis -> {
                    while (operatorStack.peek() != "(") {
                        val operatorO1 = operatorStack.pop()
                        outputQueue.offer(operatorO1)
                    }
                    operatorStack.pop()
                }
                containsLeftParenthesis -> {
                    operatorStack.push("(")
                }
                isOperatorO1 -> {
                    val operatorO1 = operatorStack.pop()
                    operatorStack.push(token.substring(i, i + 1))
                    outputQueue.offer(operatorO1)
                }
                containsOperator -> {
                    operatorStack.push(token.substring(i, i + 1))
                }
            }
        }

        val output = outputQueue.joinToString(separator = " ")
        val operatorOutput = operatorStack.joinToString(separator = " ").reversed()

        println(output)
        println(operatorOutput)
        val append = StringBuilder(output).append(" $operatorOutput")
        println("Saída: $append")
    }

    private fun isPrecedence(newOperator: String, stackOperator: String): Boolean = when {
        newOperator == stackOperator -> {
            false
        }
        newOperator == "^" -> {
            false
        }
        newOperator == "+" -> {
            false
        }
        newOperator == "-" -> {
            false
        }
        newOperator == "x" && (
                stackOperator == "+" ||
                        stackOperator == "^") -> {
            false
        }
        newOperator == "/" && (
                stackOperator == "+" ||
                        stackOperator == "^") -> {
            false
        }
        else -> {
            true
        }
    }
}

fun main() {
    val shuntYard = ShuntYard()
    print("Informe a expressão: ")
    shuntYard.readToken()
}