package winter_camp_afterburner.interface_adapter.repository.converter

import winter_camp_afterburner.interface_adapter.repository.ObjectListReader
import winter_camp_afterburner.interface_adapter.repository.converter.dao.ListFileOpenHelper


abstract class CSVFileReader<out T>(fileName: String) : ObjectListReader<T> {
    private val reader: ObjectListReader<String> =
        ListFileOpenHelper(
            fileName
        )
    override fun getObjectListFromFile(): List<T> = reader.getObjectListFromFile().map { parseCSVToObject(it.split(",")) }

    protected abstract fun parseCSVToObject(data: List<String>): T
}
