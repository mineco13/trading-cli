package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*

interface Inventory:Portfolio<Balance>{
    interface Mutable:Inventory,PortfolioMutable<Balance>
}
