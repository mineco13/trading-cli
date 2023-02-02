package winter_camp_afterburner

import winter_camp_afterburner.interface_adapter.input_receiver.*
import winter_camp_afterburner.interface_adapter.presenter.*
import winter_camp_afterburner.interface_adapter.repository.*
import winter_camp_afterburner.interface_adapter.repository.converter.*
import winter_camp_afterburner.usecases.*
import winter_camp_afterburner.usecases.inventry_control.impl.*
import winter_camp_afterburner.usecases.model.impl.*
import winter_camp_afterburner.usecases.trade.impl.*
import winter_camp_afterburner.usecases.ui.impl.MenuInteractor
import kotlin.system.exitProcess


fun main() {
    //
    val issueInfoInteractor = IssueInfoInteractorImpl(IssueInfoMapRepository(IssueListReader("master_issue.txt")))
    val balanceMapInteractor = BalanceMapInteractorImpl(issueInfoInteractor.issueInfoMap)
    val inventoryInteractor = InventoryInteractorImpl(
        BalanceMapRepository(BalanceListWriter("balance.txt", issueInfoInteractor.issueInfoMap)),
        issueInfoInteractor.issueInfoMap,
        balanceMapInteractor.balanceMap
    )
    val transactionHistoryInteractor = TransactionHistoryInteractorImpl(
        TransactionHistoryRepository(PositionListWriter("transaction_history.txt", issueInfoInteractor.issueInfoMap)),
        balanceMapInteractor.balanceMap,
        inventoryInteractor.inventoryMutable
    )

    //
    val timeRepository = TimeRepository(SystemLogWriter("system_log.txt"))

    //
    val showBalanceInteractor = ShowBalanceInteractor(ShowBalancePresenter, balanceMapInteractor)
    val markToMarketInteractor = MarkToMarketInteractor(InputReceiver, MarkToMarketPresenter, balanceMapInteractor)
    val inputStockInteractor =
        InputStockInteractor(
            InputReceiver,
            InputStockPresenter,
            issueInfoInteractor,
            inventoryInteractor,
            balanceMapInteractor
        )

    //
    val tradeInteractor =
        TradeInteractor(
            InputReceiver,
            TradePresenter,
            issueInfoInteractor,
            transactionHistoryInteractor.transactionHistory,
            //balanceMapInteractor
            inventoryInteractor
        )
    val cancelInteractor = CancelInteractor(
        InputReceiver,
        CancelPresenter,
        issueInfoInteractor,
        balanceMapInteractor,
        transactionHistoryInteractor
    )
    val finalClosingInteractor = FinalClosingInteractor(
        timeRepository,
        InputReceiver,
        FinalClosingPresenter,
        transactionHistoryInteractor,
        inventoryInteractor
    )

    //
    val SHOW_BALANCE = MenuInteractor.MenuStructure("s", "保有銘柄残高一覧", showBalanceInteractor)
    val MARK_TO_MARKET = MenuInteractor.MenuStructure("m", "値洗い", markToMarketInteractor)
    val INPUT_STOCK = MenuInteractor.MenuStructure("i", "在庫データ入力", inputStockInteractor)
    val TRADE = MenuInteractor.MenuStructure("t", "取引", tradeInteractor)
    val CANCEL = MenuInteractor.MenuStructure("c", "キャンセル", cancelInteractor)
    val FINAL_CLOSING = MenuInteractor.MenuStructure(
        MenuInteractor.FINAL_CLOSING_KEY, "締め処理", finalClosingInteractor)
    val END = MenuInteractor.MenuStructure("e", "終了", object : UseCase {
        override fun run() = println("終了します").run { exitProcess(0) }
    })
    val menus = listOf(SHOW_BALANCE, MARK_TO_MARKET, INPUT_STOCK, TRADE, CANCEL, FINAL_CLOSING, END)
    MenuInteractor(
        menus,
        timeRepository,
        InputReceiver,
        MenuPresenter
    ).run()

}
