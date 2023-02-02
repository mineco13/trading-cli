package winter_camp_afterburner.interface_adapter.repository

import winter_camp_afterburner.entities.issues.*
import winter_camp_afterburner.usecases.model.impl.TransactionHistoryInteractorImpl

class TransactionHistoryRepository(private val transactionHistoryFileReader: ObjectListWriter<Position>) :
    TransactionHistoryInteractorImpl.Repository {
    override fun getMutableListOfTransaction(): MutableList<Position> =
        transactionHistoryFileReader.getObjectListFromFile().toMutableList()

    override fun saveTransactionHistory(transactionHistory: List<Position>) =
        transactionHistoryFileReader.writeFileFromObjectList(transactionHistory)
}