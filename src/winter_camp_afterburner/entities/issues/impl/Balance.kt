package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*
import java.math.BigDecimal

interface Balance : Position, IssueInfo {
    override var marketValue: BigDecimal?
    val marketCapitalization: BigDecimal?
    val profitAndLoss: BigDecimal?
    override fun plus(other: Position): Balance
}
