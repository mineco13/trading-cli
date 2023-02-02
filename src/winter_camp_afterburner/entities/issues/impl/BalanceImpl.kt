package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*
import java.math.BigDecimal

class BalanceImpl(private val position: Position, private val info: IssueInfo) : Balance, Position by position,
    IssueInfo by info {
    override val issue: Issue = info.issue
    override val marketCapitalization: BigDecimal?
        get() = marketValue?.times(amount)
    override val profitAndLoss: BigDecimal?
        get() = marketCapitalization?.minus(bookCapitalization)

    init {
        require(position.issue == info.issue)
        require(amount >= BigDecimal.ZERO)
    }

    override operator fun plus(other: Position): Balance = BalanceImpl(position + other, info)

}
