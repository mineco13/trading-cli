package winter_camp_afterburner.usecases.trade.impl

import winter_camp_afterburner.usecases.UseCase
import winter_camp_afterburner.usecases.model.*

/**
 * 各担当に指示を出し、締め処理機能を遂行します
 */
class FinalClosingInteractor(
    private val repository: Repository,
    private val inputReceiver: InputReceiver,
    private val presenter: Presenter,
    private val transactionHistoryInteractor: TransactionHistoryInteractor,
    private val inventoryInteractor: InventoryInteractor
) : UseCase {

    override fun run() {
        if (!getAccept()) return
        transactionHistoryInteractor.run {
            transactionHistory.clear()
            saveTransactionHistory()
        }
        inventoryInteractor.saveInventory()
        repository.stampTime()
    }


    private fun getAccept(): Boolean = try {
        presenter.showQuestionMessage()
        inputReceiver.getAccept()
    } catch (e: Exception) {
        presenter.showTryAgainMessage()
        getAccept()
    }


    interface Repository {
        fun stampTime()
    }

    interface InputReceiver {
        fun getAccept(): Boolean
    }

    interface Presenter {
        fun showQuestionMessage()
        fun showTryAgainMessage()
    }

}
