package logica

import model.Cliente
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.NoSuchFileException

class Writer : IWriter<Cliente> {

    override fun writeCsv(path: String, set: Collection<Cliente>) {
        try {
            val writer = File(path).bufferedWriter()
            writer.write("AGÊNCIA, CONTA, BANCO, TITULAR, OPERAÇÃO, HORA, SALDO FINAL,")
            writer.newLine()
            set.forEach { cliente ->
                writer.write(
                    "${cliente.agencia}," +
                            "${cliente.conta}," +
                            "${cliente.banco}," +
                            "${cliente.titular}," +
                            "${cliente.operacao}," +
                            "${cliente.hora}," +
                            "${cliente.saldo}"
                )
                writer.newLine()
            }

            writer.flush()
            writer.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchFileException) {
            e.printStackTrace()
        }
        println("\nSalvo no arquivo csv:$path")
    }
}