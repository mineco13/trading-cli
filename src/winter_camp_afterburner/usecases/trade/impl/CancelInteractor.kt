package winter_camp_afterburner.usecases.trade.impl

import winter_camp_afterburner.entities.issues.*
import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.UseCase
import winter_camp_afterburner.usecases.model.BalanceMapInteractor
import winter_camp_afterburner.usecases.model.IssueInfoInteractor
import winter_camp_afterburner.usecases.model.TransactionHistoryInteractor
import java.math.BigDecimal

/**
 * 各担当に指示を出し、取り消し機能を遂行します
 */
class CancelInteractor(
    private val inputReceiver: InputReceiver,
    private val presenter: Presenter,
    private val issueInfoInteractor: IssueInfoInteractor,
    private val balanceMapInteractor: BalanceMapInteractor,
    private val transactionHistoryInteractor: TransactionHistoryInteractor
) : UseCase {
    override fun run() {
        val bond = getBondFromInput()
        transactionHistoryInteractor.transactionHistory.filter(bond).let {
            if (it.isNotEmpty()) {
                val maxAmountToCancelBuyOrder = balanceMapInteractor.balanceMap.getOrZero(bond).amount
                val cancelPosition = it.getRemovePosition(maxAmountToCancelBuyOrder)
                transactionHistoryInteractor.transactionHistory.remove(cancelPosition)
            }
        }
    }


    private fun getBondFromInput(): Bond = try {
        presenter.showInputCodeMessage()
        issueInfoInteractor.issueInfoMap[inputReceiver.getInput()]?.issue as Bond
    } catch (e: java.lang.Exception) {
        presenter.showTryAgainMessage()
        getBondFromInput()
    }


    private fun List<Position>.getRemovePosition(maxAmountToCancelBuyOrder: BigDecimal): Position = try {
        presenter.run {
            showCandidateList(this@getRemovePosition)
            showPickCancelTargetMessage()
        }
        this[inputReceiver.getIndex()].apply { require(maxAmountToCancelBuyOrder - amount >= BigDecimal.ZERO) }
    } catch (e: Exception) {
        getRemovePosition(maxAmountToCancelBuyOrder)
    }

    interface InputReceiver {
        fun getInput(): String
        fun getIndex(): Int
    }

    interface Presenter {
        fun showInputCodeMessage()
        fun showPickCancelTargetMessage()
        fun showCandidateList(candidates: List<Position>)
        fun showTryAgainMessage()
    }

}