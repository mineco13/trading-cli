package winter_camp_afterburner.interface_adapter.presenter

import winter_camp_afterburner.entities.issues.Position
import winter_camp_afterburner.usecases.trade.impl.CancelInteractor

object CancelPresenter : CancelInteractor.Presenter {
    override fun showInputCodeMessage() = println("銘柄コードを入力してください。")


    override fun showPickCancelTargetMessage() = println("取り消したい取引を選んでください")

    override fun showCandidateList(candidates: List<Position>) {
        for ((count, p) in candidates.withIndex()) {
            println(count.toString() + " : 取引数量: " + p.amount + " , 取引価格: " + p.bookValue)
        }
    }

    override fun showTryAgainMessage() = println("入力値が不正です。入力しなおしてください。")
}
