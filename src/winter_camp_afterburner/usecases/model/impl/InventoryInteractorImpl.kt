package winter_camp_afterburner.usecases.model.impl

import winter_camp_afterburner.entities.issues.*
import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.model.*
import java.math.BigDecimal

class InventoryInteractorImpl(
    private val repository: Repository,
    private val issueInfoMap: IssueInfoMap,
    private val balanceMap: BalanceMapMutable
) : InventoryInteractor {
    override val inventoryMutable: InventoryMutable = InventoryImplSynchronized(repository.getInventory())

    override fun saveInventory() =
        inventoryMutable.filterNot { it.value.amount == BigDecimal.ZERO }.let { repository.saveInventory(it) }


    inner class InventoryImplSynchronized(balanceMap: MutableMap<Issue, Balance>) : PortfolioImpl<Balance>(balanceMap),
        InventoryMutable {
        init {
            this@InventoryInteractorImpl.balanceMap.addAll(values)
        }

        override fun add(element: Position): Boolean = if (super.add(element)) balanceMap.add(element) else false
        override fun getOrZero(issue: Issue): Balance =
            get(issue) ?: BalanceImpl(PositionImpl(issue, BigDecimal.ZERO, BigDecimal.ZERO), issueInfoMap[issue.code]!!)

        override fun Position.converted(): Balance = BalanceImpl(this, issueInfoMap[issue.code]!!)
    }

    interface Repository {
        fun getInventory(): MutableMap<Issue, Balance>
        fun saveInventory(inventory: Map<Issue, Balance>)
    }

}
