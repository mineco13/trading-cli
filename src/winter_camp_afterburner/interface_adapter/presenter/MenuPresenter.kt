package winter_camp_afterburner.interface_adapter.presenter

import winter_camp_afterburner.usecases.ui.impl.MenuInteractor

object MenuPresenter : MenuInteractor.IPresenter {

    override fun showSelectMenuMessage() =println("いずれかの文字を打ち込んでメニューを選択してください。")
    override fun showMenus(structures: List<MenuInteractor.MenuStructure>) =structures.forEach { println(it.key + " : " + it.message) }
    override fun showTryAgainMessage() =println("入力値が不正です。入力しなおしてください。")
}