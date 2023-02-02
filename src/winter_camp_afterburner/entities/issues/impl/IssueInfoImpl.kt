package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*
import java.math.BigDecimal

class IssueInfoImpl(override val issue: Issue, marketValue: BigDecimal? = null) : IssueInfo {
    override var marketValue: BigDecimal? = null
        set(value) {
            value?.let { require(it >= BigDecimal.ZERO) }
            field = value
        }

    init {
        this.marketValue = marketValue
    }
}
