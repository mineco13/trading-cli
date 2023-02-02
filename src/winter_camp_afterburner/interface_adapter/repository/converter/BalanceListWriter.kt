package winter_camp_afterburner.interface_adapter.repository.converter

import winter_camp_afterburner.entities.issues.impl.Balance
import winter_camp_afterburner.entities.issues.impl.BalanceImpl
import winter_camp_afterburner.entities.issues.impl.IssueInfoMap
import winter_camp_afterburner.entities.issues.impl.PositionImpl
import java.math.BigDecimal

class BalanceListWriter(fileName: String, private val issueInfoMap: IssueInfoMap) : CSVFileWriter<Balance>(fileName) {

    override fun parseCSVToObject(data: List<String>): BalanceImpl = BalanceImpl(
        PositionImpl(
            issue = issueInfoMap[data[0]]!!.issue,
            amount = BigDecimal(data[1]),
            bookValue = BigDecimal(data[2])
        ), info = issueInfoMap[data[0]]!!
    ).apply { marketValue = if (data[3] != "null") BigDecimal(data[3]) else null }

    override fun parseObjectToCSV(obj: Balance): List<Any> = obj.run {
        listOf(issue.code, amount, bookValue, marketValue.toString())
    }
}
