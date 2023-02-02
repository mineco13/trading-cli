package winter_camp_afterburner.usecases.model.impl

import winter_camp_afterburner.entities.issues.*
import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.model.*
import java.math.BigDecimal

class BalanceMapInteractorImpl(private val issueInfoMap: IssueInfoMap) : BalanceMapInteractor {
    override val balanceMap: BalanceMapMutable = BalanceMapImplSynchronized(mutableMapOf())

    private inner class BalanceMapImplSynchronized(balanceMap: MutableMap<Issue, Balance>) :
        PortfolioImpl<Balance>(balanceMap), BalanceMapMutable {

        override fun getOrZero(issue: Issue): Balance =
            get(issue) ?: BalanceImpl(PositionImpl(issue, BigDecimal.ZERO, BigDecimal.ZERO), issueInfoMap[issue.code]!!)

        override fun Position.converted(): Balance = BalanceImpl(this, issueInfoMap[issue.code]!!)
    }
}
