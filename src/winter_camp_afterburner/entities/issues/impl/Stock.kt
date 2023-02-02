package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*

class Stock(code: String, name: String, val market: Market) : Issue(code, name) {

    override val issueType = IssueType.STOCK

    override fun equals(other: Any?): Boolean =
        if (other is Stock) super.equals(other) && market == other.market else false

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (market.hashCode())
        return result
    }

    override fun toString() = "Stock{${super.toString()}, market=$market}"

    interface Market

}