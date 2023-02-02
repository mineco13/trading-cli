package winter_camp_afterburner.interface_adapter.presenter

import winter_camp_afterburner.usecases.trade.impl.FinalClosingInteractor

object FinalClosingPresenter : FinalClosingInteractor.Presenter {
    override fun showQuestionMessage() {
        println("締め処理を行いますか？")
        println("y :はい")
        println("n :いいえ")
    }

    override fun showTryAgainMessage() =println("入力値が不正です。入力しなおしてください。")
}