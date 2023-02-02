package winter_camp_afterburner.interface_adapter.repository.converter

import winter_camp_afterburner.entities.issues.impl.Bond
import java.math.BigDecimal

class IssueListReader(fileName:String): CSVFileReader<Bond>(fileName) {
    override fun parseCSVToObject(data: List<String>): Bond =
        Bond(
            code = data[0],
            name = data[1],
            maturity = data[2].toInt(),
            coupon = BigDecimal(data[3]),
            couponChance = data[4].toInt()
        )
}
