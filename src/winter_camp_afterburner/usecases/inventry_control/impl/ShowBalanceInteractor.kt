package winter_camp_afterburner.usecases.inventry_control.impl

import winter_camp_afterburner.entities.issues.*
import winter_camp_afterburner.entities.issues.impl.*
import winter_camp_afterburner.usecases.UseCase
import winter_camp_afterburner.usecases.model.BalanceMapInteractor

/**
 * 各担当に指示を出し、残高一覧を表示する機能を遂行します
 */
class ShowBalanceInteractor(
    /**表示担当*/
    private val presenter: Presenter, private val balanceMapInteractor: BalanceMapInteractor
) : UseCase {

    /**残高一覧を表示します。*/
    override fun run() = balanceMapInteractor.balanceMap.let {
        if (it.isEmpty()) presenter.showNothingMessage()
        else presenter.showPortfolio(it)
    }

    interface Presenter {
        fun showPortfolio(portfolio: Portfolio<Balance>)
        fun showNothingMessage()
    }
}