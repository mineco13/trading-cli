package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*
import java.math.BigDecimal

class PositionImpl(override val issue: Issue, override val amount: BigDecimal, bookValue: BigDecimal) : Position, Comparable<PositionImpl> {
    override val bookValue: BigDecimal

    init {
        require(bookValue >= BigDecimal.ZERO)
        this.bookValue = bookValue
    }

    override fun toString() = "Position(issue=$issue, amount=$amount, bookValue=$bookValue)"


    override fun equals(other: Any?) =
        if (other is PositionImpl) (issue == other.issue && amount == other.amount && bookValue == other.bookValue) else false

    override fun compareTo(other: PositionImpl): Int = issue.code.compareTo(other.issue.code)

    override operator fun plus(other: Position): PositionImpl {
        require(issue == other.issue)
        val sumValue = amount * bookValue + other.amount * other.bookValue
        val sumAmount = amount.add(other.amount)
        val bookValue = if (other.amount > BigDecimal.ZERO) sumValue / sumAmount
        else bookValue
        return PositionImpl(issue, sumAmount, bookValue)
    }

    override fun hashCode(): Int {
        var result = issue.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + bookValue.hashCode()
        return result
    }
}
