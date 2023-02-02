package winter_camp_afterburner.entities.issues.impl

import java.time.LocalDateTime

class Transaction(private val tradeTime: LocalDateTime, val position: PositionImpl) : Comparable<Transaction> {
    override fun equals(other: Any?) =
        if (other is Transaction) (tradeTime == other.tradeTime && position == other.position) else false

    override fun compareTo(other: Transaction) = tradeTime.compareTo(other.tradeTime)
}