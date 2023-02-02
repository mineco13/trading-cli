package winter_camp_afterburner.interface_adapter.repository.converter

import winter_camp_afterburner.interface_adapter.repository.ObjectListWriter
import winter_camp_afterburner.interface_adapter.repository.converter.dao.ListFileOpenHelper
import java.time.LocalDate

/**
 * システムログファイルの入出力をします
 */
class SystemLogWriter(fileName:String) :
    ObjectListWriter<LocalDate> {
    private val writer=ListFileOpenHelper(fileName)

    override fun getObjectListFromFile(): List<LocalDate> =writer.getObjectListFromFile().map { LocalDate.parse(it) }
    override fun writeFileFromObjectList(obj: List<LocalDate>) =obj.map { it.toString() }.let{writer.writeFileFromObjectList(it)}
}
