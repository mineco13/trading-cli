package winter_camp_afterburner.interface_adapter.presenter

import winter_camp_afterburner.entities.issues.Issue
import winter_camp_afterburner.usecases.inventry_control.impl.MarkToMarketInteractor

object MarkToMarketPresenter : MarkToMarketInteractor.Presenter {
    override fun showCodeAndName(issue: Issue) = println("銘柄コード: " + issue.code + ", 銘柄名: " + issue.name)
    override fun showTryAgainMessage() = println("入力値が不正です。入力しなおしてください。")
}