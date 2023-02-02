package winter_camp_afterburner.interface_adapter.presenter

import winter_camp_afterburner.usecases.inventry_control.impl.InputStockInteractor

object InputStockPresenter : InputStockInteractor.Presenter {
    override fun showInputCodeMessage() = println("銘柄コードを入力してください。")
    override fun showInputAmountMessage() = println("数量を入力してください。")
    override fun showInputBookValueMessage() = println("簿価を入力してください。")
    override fun showTryAgainMessage() = println("入力値が不正です。入力しなおしてください。")

}