package winter_camp_afterburner.interface_adapter.repository

import winter_camp_afterburner.entities.issues.Issue
import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.model.impl.InventoryInteractorImpl

class BalanceMapRepository(private val balanceFileReader: ObjectListWriter<Balance>) : InventoryInteractorImpl.Repository {
    override fun getInventory(): MutableMap<Issue, Balance> =
        balanceFileReader.getObjectListFromFile().associateBy { it.issue }.toMutableMap()

    override fun saveInventory(inventory: Map<Issue, Balance>) =
        balanceFileReader.writeFileFromObjectList(inventory.values.toList())
}
