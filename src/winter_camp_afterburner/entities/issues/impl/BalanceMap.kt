package winter_camp_afterburner.entities.issues.impl

import winter_camp_afterburner.entities.issues.*

interface BalanceMap : Portfolio<Balance>{
    interface Mutable : PortfolioMutable<Balance>, BalanceMap
}