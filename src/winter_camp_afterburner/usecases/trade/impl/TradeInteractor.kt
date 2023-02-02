package winter_camp_afterburner.usecases.trade.impl

import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.*
import winter_camp_afterburner.usecases.model.*
import java.math.BigDecimal

/**
 * 各担当に指示を出し、取引機能を遂行します
 */
class TradeInteractor(
    private val inputReceiver: InputReceiver,
    private val presenter: Presenter,
    private val issueInfoInteractor: IssueInfoInteractor,
    private val transactionHistoryMutable: TransactionHistoryMutable,
    //private val balanceMapInteractor: BalanceMapInteractor
private val inventoryInteractor: InventoryInteractor
) : UseCase {


    override fun run() {
        val bond = getBondFromInput()
//        val (amount, bookValue) = balanceMapInteractor.balanceMap.getOrZero(bond).run { Pair(amount, bookValue) }
        val (amount, bookValue) = inventoryInteractor.inventoryMutable.getOrZero(bond).run { Pair(amount, bookValue) }
        val trade = getPositionFromInput(bond, amount)
        presenter.showTrade(trade.bookCapitalization)
        if (trade.amount < BigDecimal.ZERO) {
            val realizedGainsAndLosses = (trade.bookValue - bookValue) * trade.amount.abs()
            presenter.showProfitAndLoss(realizedGainsAndLosses)
        }
        transactionHistoryMutable.add(trade)
        Unit
    }

    private fun getBondFromInput(): Bond = repeatUntilReturnCorrectValue {
        presenter.showInputCodeMessage()
        val code = inputReceiver.getInput()
        issueInfoInteractor.issueInfoMap[code]?.issue as Bond
    }


    private fun getPositionFromInput(bond: Bond, sumAmount: BigDecimal): PositionImpl {
        val sell = getSellOrNotFromInput(sumAmount)
        val bookValue = getBookValueFromInput()
        val amount = if (sell) getSellAmountFromInput(sumAmount) else getBuyAmountFromInput()
        return PositionImpl(bond, amount, bookValue)
    }

    private fun getSellOrNotFromInput(sumAmount: BigDecimal): Boolean = repeatUntilReturnCorrectValue {
        presenter.showInputBuyOrSellMessage()
        inputReceiver.getSellOrNot().also { isSell -> require(!(sumAmount.signum() <= 0 && isSell)) }
    }

    private fun getBookValueFromInput(): BigDecimal = repeatUntilReturnCorrectValue {
        presenter.showInputBookValueMessage()
        inputReceiver.getNonNegativeBigDecimal()
    }

    private fun getBuyAmountFromInput(): BigDecimal = repeatUntilReturnCorrectValue {
        presenter.showInputBuyAmountMessage()
        inputReceiver.getNonNegativeBigDecimal()
    }

    private fun getSellAmountFromInput(maxAmountToSell: BigDecimal): BigDecimal = repeatUntilReturnCorrectValue {
        presenter.showInputSellAmountMessage()
        val amount = inputReceiver.getNonNegativeBigDecimal()
        require(amount <= maxAmountToSell)
        amount.negate()
    }


    private fun <T> repeatUntilReturnCorrectValue(f: () -> T): T = try {
        f()
    } catch (e: Exception) {
        presenter.showTryAgainMessage()
        repeatUntilReturnCorrectValue(f)
    }

    interface InputReceiver {
        fun getInput(): String
        fun getNonNegativeBigDecimal(): BigDecimal
        fun getSellOrNot(): Boolean
    }

    interface Presenter {
        fun showInputCodeMessage()
        fun showInputBuyOrSellMessage()
        fun showInputBuyAmountMessage()
        fun showInputSellAmountMessage()
        fun showInputBookValueMessage()
        fun showTrade(bookCapitalization: BigDecimal)
        fun showProfitAndLoss(profitAndLoss: BigDecimal)
        fun showTryAgainMessage()
    }

}