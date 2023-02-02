package winter_camp_afterburner.usecases.model.impl

import winter_camp_afterburner.entities.issues.*
import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.model.*

class TransactionHistoryInteractorImpl(
    private val repository: Repository,
    private val balances: BalanceMapMutable,
    private val inventory: InventoryMutable
) : TransactionHistoryInteractor {

    override val transactionHistory: TransactionHistoryMutable =
        TransactionHistoryImplSynchronized(repository.getMutableListOfTransaction())

    override fun saveTransactionHistory() = repository.saveTransactionHistory(transactionHistory)

    inner class TransactionHistoryImplSynchronized(private val positionList: MutableList<Position>) :
        TransactionHistoryMutable, MutableList<Position> by positionList {

        init {
            balances.addAll(positionList)
        }

        override fun add(element: Position): Boolean = if (positionList.add(element)) balances.add(element) else false


        override fun addAll(elements: Collection<Position>): Boolean = elements.forEach { add(it) }.run { true }

        //todo O(N+M) help me.
        override fun remove(element: Position): Boolean = positionList.remove(element).apply {
            balances.run {
                clear()
                addAll(inventory.values)
                addAll(positionList)
            }
        }

        override fun clear() = positionList.run {
            inventory.run {
                clear()
                putAll(balances)
            }
            clear()
        }

        override fun add(index: Int, element: Position) = throw IllegalAccessError()
        override fun addAll(index: Int, elements: Collection<Position>): Boolean = throw IllegalAccessError()
        override fun set(index: Int, element: Position): Position = throw IllegalAccessError()
        override fun filter(issue: Issue): TransactionHistory =
            TransactionHistoryImplSynchronized(filter { it.issue == issue }.toMutableList())
    }


    interface Repository {
        fun getMutableListOfTransaction(): MutableList<Position>
        fun saveTransactionHistory(transactionHistory: List<Position>)
    }
}
