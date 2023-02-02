package winter_camp_afterburner.usecases.inventry_control.impl

import winter_camp_afterburner.entities.issues.Issue
import winter_camp_afterburner.entities.issues.impl.Balance
import winter_camp_afterburner.usecases.UseCase
import winter_camp_afterburner.usecases.model.BalanceMapInteractor
import java.math.BigDecimal

/**
 * 各担当に指示を出し、値洗い機能を遂行します
 */
class MarkToMarketInteractor(
    private val inputReceiver: InputReceiver,
    private val presenter: Presenter,
    private val balanceMapInteractor: BalanceMapInteractor
) : UseCase {

    override fun run() = balanceMapInteractor.balanceMap.forEach {
        presenter.showCodeAndName(it.key)
        inputMarketValue(it.value)
    }


    private fun inputMarketValue(balance: Balance): Unit = try {
        inputReceiver.getNonNegativeBigDecimal().let { balance.marketValue = it }
    } catch (e: Exception) {
        presenter.showTryAgainMessage()
        inputMarketValue(balance)
    }

    interface InputReceiver {
        fun getNonNegativeBigDecimal(): BigDecimal
    }

    interface Presenter {
        fun showCodeAndName(issue: Issue)
        fun showTryAgainMessage()
    }

}
