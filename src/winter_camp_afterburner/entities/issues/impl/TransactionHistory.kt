package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*

//todo change Position to Transaction
interface TransactionHistory : List<Position> {
    fun filter(issue: Issue): TransactionHistory
    interface Mutable : TransactionHistory, MutableCollection<Position>
}

