package winter_camp_afterburner.interface_adapter.repository.converter

import winter_camp_afterburner.interface_adapter.repository.ObjectListWriter
import winter_camp_afterburner.interface_adapter.repository.converter.dao.ListFileOpenHelper

abstract class CSVFileWriter<T>(fileName: String) : CSVFileReader<T>(fileName),
    ObjectListWriter<T> {
    private val writer =
        ListFileOpenHelper(
            fileName
        )

    override fun writeFileFromObjectList(obj: List<T>) =
        obj.map { parseCSVToString(parseObjectToCSV(it)) }.let { writer.writeFileFromObjectList(it) }

    private fun parseCSVToString(data: List<Any>): String = StringBuilder().apply {
        data.forEach { append("$it,") }
        deleteCharAt(lastIndex)
    }.toString()

    protected abstract fun parseObjectToCSV(obj: T): List<Any>
}
