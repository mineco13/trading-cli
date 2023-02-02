package winter_camp_afterburner.usecases.model

import winter_camp_afterburner.entities.issues.impl.TransactionHistoryMutable

interface TransactionHistoryInteractor {
    val transactionHistory: TransactionHistoryMutable
    fun saveTransactionHistory()
}
