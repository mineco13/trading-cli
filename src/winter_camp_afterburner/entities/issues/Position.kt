package winter_camp_afterburner.entities.issues

import java.math.BigDecimal

interface Position {
    val issue: Issue
    val amount: BigDecimal
    val bookValue: BigDecimal

    val bookCapitalization: BigDecimal
        get() = bookValue * amount

    operator fun plus(other: Position): Position
}