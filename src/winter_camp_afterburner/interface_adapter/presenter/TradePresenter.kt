package winter_camp_afterburner.interface_adapter.presenter

import winter_camp_afterburner.usecases.trade.impl.TradeInteractor
import java.math.BigDecimal

object TradePresenter : TradeInteractor.Presenter {
    override fun showInputCodeMessage() = println("銘柄コードを入力してください。")


    override fun showInputBuyOrSellMessage() {
        println("売りか買いかを入力してください")
        println("b : 買い")
        println("s : 売り")
    }

    override fun showInputBuyAmountMessage() = println("購入する数量を入力してください。")
    override fun showInputSellAmountMessage() = println("売却する数量を入力してください。")

    override fun showInputBookValueMessage() = println("価格を入力してください。")

    override fun showTrade(bookCapitalization: BigDecimal) {
        System.out.printf("取引価格: %,16d\n", bookCapitalization.abs().toLong())
    }

    override fun showProfitAndLoss(profitAndLoss: BigDecimal) {
        print("実現損益: ")
        try {
            System.out.printf("%,16d\n", profitAndLoss.toLong())
        } catch (e: Exception) {
            println("N/A")
        }
    }

    override fun showTryAgainMessage() = println("入力値が不正です。入力しなおしてください。")
}