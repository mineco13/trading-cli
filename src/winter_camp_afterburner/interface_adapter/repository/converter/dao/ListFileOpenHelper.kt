package winter_camp_afterburner.interface_adapter.repository.converter.dao

import winter_camp_afterburner.interface_adapter.repository.ObjectListWriter
import java.io.*

/**行のリストをテキストファイルに読み書きします。*/
class ListFileOpenHelper(private val fileName: String) : ObjectListWriter<String> {
    private val pathRevision = "./src/winter_camp_afterburner/data/"


    override fun getObjectListFromFile(): List<String> = //mutableListOf<String>().apply {
//        BufferedReader(FileReader(pathRevision + fileName)).use {
//            while (it.ready()) add(it.readLine())
//        }
//    }
        File(pathRevision + fileName).readLines()


//    override fun writeFileFromObjectList(obj: List<String>) =
//        PrintWriter(BufferedWriter(FileWriter(pathRevision + fileName))).use { pw -> obj.forEach { pw.println(it) } }

    override fun writeFileFromObjectList(obj: List<String>) =
        StringBuilder().apply { obj.forEach { append("$it\n") } }.toString()
            .let { File(pathRevision + fileName).writeText(it) }

}
