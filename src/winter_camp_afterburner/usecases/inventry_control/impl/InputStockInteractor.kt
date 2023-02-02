package winter_camp_afterburner.usecases.inventry_control.impl

import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.UseCase
import winter_camp_afterburner.usecases.model.BalanceMapInteractor
import winter_camp_afterburner.usecases.model.InventoryInteractor
import winter_camp_afterburner.usecases.model.IssueInfoInteractor
import java.math.BigDecimal

/**
 * 各担当に指示を出し、在庫入力機能を遂行します
 */
class InputStockInteractor(
    private val inputReceiver: InputReceiver,
    private val presenter: Presenter,
    private val issueInfoInteractor: IssueInfoInteractor,
    private val inventoryInteractor: InventoryInteractor,
    private val balanceMapInteractor: BalanceMapInteractor
) : UseCase {
    override fun run() = inventoryInteractor.inventoryMutable.run {
        getPositionFromInput().let { add(it) }
        Unit
    }

    private fun getPositionFromInput(): PositionImpl {
        val bond = getBondFromInput(issueInfoInteractor.issueInfoMap)

        val amountOnInventory = inventoryInteractor.inventoryMutable.getOrZero(bond).amount
        val amountOnBalance = balanceMapInteractor.balanceMap.getOrZero(bond).amount

        val upperAmount = minOf(amountOnInventory, amountOnBalance)
        val amount = getAmountFromInput(upperAmount)
        val bookValue = getBookValueFromInput()
        return PositionImpl(bond, amount, bookValue)
    }

    private fun getBondFromInput(issues: IssueInfoMap): Bond = repeatUntilReturnCorrectValue {
        presenter.showInputCodeMessage()
        val code = inputReceiver.getInput()
        issues[code]?.issue as Bond
    }

    private fun getAmountFromInput(maxAmountToCancel: BigDecimal): BigDecimal = repeatUntilReturnCorrectValue {
        presenter.showInputAmountMessage()
        inputReceiver.getBigDecimal().also { require(it + maxAmountToCancel >= BigDecimal.ZERO) }
    }


    private fun getBookValueFromInput(): BigDecimal = repeatUntilReturnCorrectValue {
        presenter.showInputBookValueMessage()
        inputReceiver.getNonNegativeBigDecimal()
    }


    private fun <T> repeatUntilReturnCorrectValue(f: () -> T): T = try {
        f()
    } catch (e: Exception) {
        presenter.showTryAgainMessage()
        repeatUntilReturnCorrectValue(f)
    }


    interface InputReceiver {
        fun getInput(): String
        fun getBigDecimal(): BigDecimal
        fun getNonNegativeBigDecimal(): BigDecimal
    }

    interface Presenter {
        fun showInputCodeMessage()
        fun showInputAmountMessage()
        fun showInputBookValueMessage()
        fun showTryAgainMessage()
    }

}