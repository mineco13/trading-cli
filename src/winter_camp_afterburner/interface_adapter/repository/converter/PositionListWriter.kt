package winter_camp_afterburner.interface_adapter.repository.converter

import winter_camp_afterburner.entities.issues.Position
import winter_camp_afterburner.entities.issues.impl.IssueInfoMap
import winter_camp_afterburner.entities.issues.impl.PositionImpl
import java.math.BigDecimal

class PositionListWriter(fileName: String, private val issueInfoMap: IssueInfoMap) :
    CSVFileWriter<Position>(fileName) {

    override fun parseCSVToObject(data: List<String>) = PositionImpl(
        issue = issueInfoMap[data[0]]!!.issue,
        amount = BigDecimal(data[1]),
        bookValue = BigDecimal(data[2])
    )


    override fun parseObjectToCSV(obj: Position) = obj.run { listOf(issue.code, amount, bookValue) }
}
