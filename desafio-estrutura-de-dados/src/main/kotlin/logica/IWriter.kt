package logica

interface IWriter<T> {
    fun writeCsv(path: String, set: Collection<T>)
}