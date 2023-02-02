package winter_camp_afterburner.usecases.model

import winter_camp_afterburner.entities.issues.impl.InventoryMutable

interface InventoryInteractor {
    val inventoryMutable: InventoryMutable
    fun saveInventory()
}
